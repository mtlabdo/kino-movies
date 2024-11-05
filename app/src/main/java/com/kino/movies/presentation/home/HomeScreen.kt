package com.kino.movies.presentation.home

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.kino.movies.presentation.designsystem.components.KinoUiNotification
import com.kino.movies.presentation.utils.UiNotification

@Composable
fun HomeScreen(
    homeViewState: HomeViewState?,
    notification: UiNotification?,
    onRefresh: () -> Unit,
    navToDetail: (movieId) -> Unit,
    modifier: Modifier = Modifier
) {
    var showNotificationDialog by remember { mutableStateOf(false) }

    LaunchedEffect(notification) {
        if (notification != null) {
            showNotificationDialog = true
        }
    }

    HomeScreenContent(
        viewState = homeViewState,
        onRefresh = onRefresh,
        toDetail = navToDetail,
        modifier = modifier
    )

    if (showNotificationDialog) {
        KinoUiNotification (
            notification = notification!!,
            onDismiss = {
                showNotificationDialog = false
            })
    }
}

typealias movieId = String