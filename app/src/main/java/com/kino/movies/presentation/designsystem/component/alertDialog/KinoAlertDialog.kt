package com.kino.movies.presentation.designsystem.component.alertDialog

import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kino.movies.presentation.utils.UiNotification

@Composable
fun KinoAlertDialog(
    dialogState: AlertDialogState,
) {
    val currentDialogData = dialogState.currentDialogData
    if (currentDialogData != null) {
        AlertDialog(currentDialogData, onDismiss = {
            dialogState.currentDialogData = null
        })
    }
}

@Composable
private fun AlertDialog(
    currentDialogData: UiNotification.DialogNotificationEvent,
    onDismiss: () -> Unit = {},
) {
    AlertDialog(icon = {
        Icon(
            currentDialogData.icon, contentDescription = null, Modifier.size(32.dp)
        )
    }, iconContentColor = currentDialogData.color, onDismissRequest = { }, title = {
        Text(
            currentDialogData.title, style = MaterialTheme.typography.headlineMedium
        )
    }, text = {
        Text(
            currentDialogData.message, style = MaterialTheme.typography.bodyLarge
        )
    }, dismissButton = {
        if (currentDialogData.negativeButtonText != null) Button(onClick = {
            currentDialogData.negativeButtonAction?.invoke()
            onDismiss()
        }) {
            Text(currentDialogData.negativeButtonText)
        }
    }, confirmButton = {
        if (currentDialogData.positiveButtonText != null) Button(onClick = {
            currentDialogData.positiveButtonAction?.invoke()
            onDismiss()
        }) {
            Text(currentDialogData.positiveButtonText)
        }
    })
}
   