package com.kino.movies.presentation.setting


sealed class SettingViewState {

    object Loading : SettingViewState()

    data class Setting(
        val theme: ThemeSetting,
        val language: LanguageSetting,
    ) : SettingViewState()
}


data class ThemeSetting(
    val settingUiItem: SettingUiItem,
    val isDarkTheme: Boolean,
)

data class LanguageSetting(
    val settingUiItem: SettingUiItem,
    val selectedLanguage: String?,
)