package com.kino.movies.presentation.setting

sealed class SettingViewState {

    object Loading : SettingViewState()

    data class SettingItems(
        val settings: List<SettingUiItem>
    ) : SettingViewState()
}

data class SettingUiItem(
    val id: String,
    val icon: Int,
    val settingName: String,
    val isSwitchUi: Boolean = false,
    val subTitle: String = "",
)