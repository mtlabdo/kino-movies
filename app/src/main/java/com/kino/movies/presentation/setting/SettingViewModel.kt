package com.kino.movies.presentation.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kino.movies.R
import com.kino.movies.domain.model.AppTheme
import com.kino.movies.domain.usecase.appLang.GetAppLangUseCase
import com.kino.movies.domain.usecase.appLang.SetAppLangUseCase
import com.kino.movies.domain.usecase.apptheme.GetAppThemeUseCase
import com.kino.movies.domain.usecase.apptheme.SetAppThemeUseCase
import com.kino.movies.presentation.utils.SystemThemeUtils
import com.kino.movies.presentation.utils.UIText
import com.kino.movies.presentation.utils.UiNotification
import com.kino.movies.presentation.utils.UiNotificationController
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingViewModel(
    private val setAppThemeUseCase: SetAppThemeUseCase,
    private val getAppThemeUseCase: GetAppThemeUseCase,
    private val setAppLanguageUseCase: SetAppLangUseCase,
    private val getAppLanguageUseCase: GetAppLangUseCase,
    private val ioDispatcher: CoroutineDispatcher,
    private val systemThemeUtils: SystemThemeUtils
) : ViewModel() {

    private val _viewState = MutableStateFlow<SettingViewState?>(null)
    val viewState = _viewState.onStart {
        observeUserPreferences()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = SettingViewState.Loading
    )


    private fun observeUserPreferences() {
        viewModelScope.launch(ioDispatcher) {
            combine<AppTheme, String?, Pair<AppTheme, String?>>(
                getAppThemeUseCase.invoke(),
                getAppLanguageUseCase.invoke()
            ) { theme, language ->
                Pair(theme, language)
            }.catch {
                UiNotificationController.sendUiNotification(
                    UiNotification.SnackBarNotificationEvent(
                        message = UIText.StringResource(R.string.error_get_preferences),
                    )
                )
            }.distinctUntilChanged().collect {
                val theme = ThemeSetting(
                    settingUiItem = darkModeSetting,
                    isDarkTheme = when (it.first) {
                        AppTheme.FOLLOW_SYSTEM -> systemThemeUtils.isSystemInDarkMode()
                        AppTheme.DARK_THEME -> true
                        AppTheme.LIGHT_THEME -> false
                    },
                )
                val languageSetting = LanguageSetting(
                    settingUiItem = languageSetting.copy(
                        subTitle = R.string.language
                    ),
                    selectedLanguage = it.second,
                )
                _viewState.value = SettingViewState.Setting(theme, languageSetting)
            }
        }
    }

    fun onEvent(event: SettingEvent) {
        when (event) {
            is SettingEvent.OnChangeTheme -> onChangeTheme(event.theme)
            is SettingEvent.OnChangeLanguage -> onChangeLanguage(event.language)
        }
    }

    private fun onChangeTheme(theme: AppTheme) {
        viewModelScope.launch(ioDispatcher) {
            setAppThemeUseCase.invoke(theme)
        }
    }

    private fun onChangeLanguage(language: Language) {
        viewModelScope.launch(ioDispatcher) {
            setAppLanguageUseCase.invoke(language.code)
        }
    }
}