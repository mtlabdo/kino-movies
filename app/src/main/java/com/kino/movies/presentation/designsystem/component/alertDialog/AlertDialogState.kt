package com.kino.movies.presentation.designsystem.component.alertDialog

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.kino.movies.presentation.utils.UiNotification


@Stable
class AlertDialogState {

    var currentDialogData by mutableStateOf<UiNotification.DialogNotificationEvent?>(null)

    fun showDialog(dialogEvent: UiNotification.DialogNotificationEvent) {
        currentDialogData = dialogEvent
    }
}