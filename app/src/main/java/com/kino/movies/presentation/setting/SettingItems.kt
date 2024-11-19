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
    subTitle = languages[0].second,
    settingName = "Langue",
)
