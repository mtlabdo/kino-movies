package com.kino.movies.presentation.setting

import com.kino.movies.R


val darkModeSetting = SettingUiItem(
    id = "dark_mode",
    icon = R.drawable.ic_launcher_foreground,
    settingName = "Mode sombre",
    isSwitchUi = true,
)

val languageSetting = SettingUiItem(
    id = "language",
    icon = R.drawable.ic_launcher_foreground,
    settingName = "Langue",
)

data class SettingUiItem(
    val id: String? = null,
    val icon: Int,
    val settingName: String,
    var subTitle: String = "",
    val isSwitchUi: Boolean = false,
    var isSwitchChecked: Boolean = false
)