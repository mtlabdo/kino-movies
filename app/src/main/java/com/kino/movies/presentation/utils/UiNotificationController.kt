package com.kino.movies.presentation.utils

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow


object UiNotificationController {

    private val _notification = Channel<UiNotification>()
    val notification = _notification.receiveAsFlow()

    suspend fun sendUiNotification(notification: UiNotification) {
        _notification.send(notification)
    }
}