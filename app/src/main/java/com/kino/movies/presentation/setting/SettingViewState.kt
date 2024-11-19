package com.kino.movies.presentation.setting

sealed class SettingViewState {

    object Loading : SettingViewState()

    data class Setting(
        val theme: SettingUiItem,
        val language: SettingUiItem,
    ) : SettingViewState()
}

