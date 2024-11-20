package com.kino.movies.presentation.utils

import androidx.compose.material3.SnackbarDuration
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

sealed class UiNotification {
    data class DialogNotificationEvent(
        val icon: ImageVector,
        val color: Color,
        val title: String,
        val message: String,
        val positiveButtonText: String? = null,
        val positiveButtonAction: (() -> Unit)? = null,
        val negativeButtonText: String? = null,
        val negativeButtonAction: (() -> Unit)? = null,
    ) :
        UiNotification()

    data class SnackBarNotificationEvent(
        val message: String,
        val duration: SnackbarDuration = SnackbarDuration.Long,
        val actionLabel: String? = null,
        val action: (() -> Unit)? = null
    ) : UiNotification()
}

