package com.kino.movies.presentation.designsystem.component

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
fun KinoUiNotification(
    notification: UiNotification,
    onDismiss: () -> Unit
) {
    val dialog = notification.toUiDialog()
    AlertDialog(
        icon = { Icon(dialog.icon, contentDescription = null, Modifier.size(32.dp)) },
        iconContentColor = dialog.color,
        onDismissRequest = { },
        title = { Text(dialog.title, style = MaterialTheme.typography.headlineMedium) },
        text = { Text(notification.message, style = MaterialTheme.typography.bodyLarge) },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("OK")
            }
        },
    )
}
