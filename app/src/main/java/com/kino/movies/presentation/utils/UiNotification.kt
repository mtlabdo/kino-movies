package com.kino.movies.presentation.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

sealed class UiNotification(
    val title: String = "Notification",
    val message: String,
) {
    class Error(message: String, title: String = "Erreur") : UiNotification(title, message)
    class Info(message: String, title: String = "Info") : UiNotification(title, message)


    fun toUiDialog(): DialogInfo {
        return when (this) {
            is Error -> DialogInfo(title, message, Icons.Default.Warning, Color.Red)
            is Info -> DialogInfo(title, message, Icons.Default.Info, Color.Blue)
        }
    }

    data class DialogInfo(
        val title: String,
        val message: String,
        val icon: ImageVector,
        val color: Color
    )
}