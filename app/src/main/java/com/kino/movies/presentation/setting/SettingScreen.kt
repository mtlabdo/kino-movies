package com.kino.movies.presentation.setting

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SettingScreen(
    settingViewState: SettingViewState?,
    modifier: Modifier,
    onEvent: (SettingEvent) -> Unit
) {
    SettingScreenContent(
        viewState = settingViewState,
        modifier = modifier,
        onEvent = onEvent
    )
}

