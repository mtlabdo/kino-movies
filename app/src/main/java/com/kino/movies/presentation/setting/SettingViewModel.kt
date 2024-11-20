package com.kino.movies.presentation.setting

import android.content.Context
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kino.movies.domain.model.AppLanguage
import com.kino.movies.domain.model.AppTheme
import com.kino.movies.domain.usecase.appLang.GetAppLangUseCase
import com.kino.movies.domain.usecase.appLang.SetAppLangUseCase
import com.kino.movies.domain.usecase.apptheme.GetAppThemeUseCase
import com.kino.movies.domain.usecase.apptheme.SetAppThemeUseCase
import com.kino.movies.presentation.utils.SystemThemeUtils
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
            combine<AppTheme, AppLanguage, Pair<AppTheme, AppLanguage>>(
                getAppThemeUseCase.invoke(),
                getAppLanguageUseCase.invoke()
            ) { theme, language ->
                Pair(theme, language)
            }.catch {
                UiNotificationController.sendUiNotification(
                    UiNotification.SnackBarNotificationEvent(
                        message = "Erreur lors de la récupération de vos préférences utilisateur !"
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
                        subTitle = languagesList.find { lang -> lang.first == it.second }?.second
                            ?: it.second.name
                    ),
                    selectedLanguage = it.second,
                    languages = languagesList,
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

    private fun onChangeLanguage(language: AppLanguage) {
        viewModelScope.launch(ioDispatcher) {
            setAppLanguageUseCase.invoke(language)
        }
    }
}