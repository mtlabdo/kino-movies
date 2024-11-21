package com.kino.movies.presentation.utils

import androidx.compose.material3.SnackbarDuration
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

sealed class UiNotification {
    data class DialogNotificationEvent(
        val icon: ImageVector,
        val color: Color,
        val title: UIText,
        val message: UIText,
        val positiveButtonText: UIText? = null,
        val positiveButtonAction: (() -> Unit)? = null,
        val negativeButtonText: UIText? = null,
        val negativeButtonAction: (() -> Unit)? = null,
    ) :
        UiNotification()

    data class SnackBarNotificationEvent(
        val message: UIText,
        val duration: SnackbarDuration = SnackbarDuration.Short,
        val actionLabel: UIText? = null,
        val action: (() -> Unit)? = null
    ) : UiNotification()
}

