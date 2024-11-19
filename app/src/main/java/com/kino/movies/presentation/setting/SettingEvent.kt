package com.kino.movies.presentation.setting

import com.kino.movies.domain.model.AppLanguage
import com.kino.movies.domain.model.AppTheme

sealed class SettingEvent {
    data class OnChangeTheme(val theme: AppTheme) : SettingEvent()
    data class OnChangeLanguage(val language: AppLanguage) : SettingEvent()
}