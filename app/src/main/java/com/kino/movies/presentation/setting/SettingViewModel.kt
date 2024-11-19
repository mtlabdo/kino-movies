package com.kino.movies.presentation.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kino.movies.domain.model.AppLanguage
import com.kino.movies.domain.model.AppTheme
import com.kino.movies.domain.usecase.appLang.GetAppLangUseCase
import com.kino.movies.domain.usecase.appLang.SetAppLangUseCase
import com.kino.movies.domain.usecase.apptheme.GetAppThemeUseCase
import com.kino.movies.domain.usecase.apptheme.SetAppThemeUseCase
import com.kino.movies.presentation.utils.UiNotification
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingViewModel(
    private val setAppThemeUseCase: SetAppThemeUseCase,
    private val getAppThemeUseCase: GetAppThemeUseCase,
    private val setAppLanguageUseCase: SetAppLangUseCase,
    private val getAppLanguageUseCase: GetAppLangUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _viewState = MutableStateFlow<SettingViewState?>(null)
    val viewState = _viewState.onStart {
        presentSettings()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = SettingViewState.Loading
    )

    private val _notification = Channel<UiNotification>()
    val notification = _notification.receiveAsFlow()

    private fun presentSettings() {
        _viewState.value = SettingViewState.SettingItems(
            settings = listOf(
                darkModeSetting,
                languageSetting,
            )
        )
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