package com.kino.movies.presentation.setting

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.kino.movies.presentation.designsystem.component.KinoUiNotification
import com.kino.movies.presentation.utils.UiNotification

@Composable
fun SettingScreen(
    settingViewState: SettingViewState?,
    notification: UiNotification?,
    modifier: Modifier,
    onEvent: (SettingEvent) -> Unit
) {
    var showNotificationDialog by remember { mutableStateOf(false) }

    LaunchedEffect(notification) {
        if (notification != null) {
            showNotificationDialog = true
        }
    }

    SettingScreenContent(
        viewState = settingViewState,
        modifier = modifier,
        onEvent = onEvent
    )

    if (showNotificationDialog) {
        KinoUiNotification(
            notification = notification!!,
            onDismiss = {
                showNotificationDialog = false
            })
    }
}

