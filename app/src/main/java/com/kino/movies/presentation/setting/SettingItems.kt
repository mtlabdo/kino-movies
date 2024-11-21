package com.kino.movies.presentation.setting

import androidx.annotation.StringRes
import com.kino.movies.R


val darkModeSetting = SettingUiItem(
    id = "dark_mode",
    icon = R.drawable.ic_dark_light,
    title = R.string.dark_mode,
    isSwitchUi = true,
)

val languageSetting = SettingUiItem(
    id = "language",
    icon = R.drawable.ic_langue,
    title = R.string.language,
)

data class SettingUiItem(
    val id: String? = null,
    val icon: Int,
    @StringRes val title: Int,
    @StringRes var subTitle: Int? = null,
    val isSwitchUi: Boolean = false,
    var isSwitchChecked: Boolean = false
)