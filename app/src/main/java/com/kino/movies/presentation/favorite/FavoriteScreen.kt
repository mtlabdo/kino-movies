package com.kino.movies.presentation.favorite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.kino.movies.presentation.designsystem.component.KinoUiNotification
import com.kino.movies.presentation.home.movieId
import com.kino.movies.presentation.utils.UiNotification

@Composable
fun FavoriteScreen(
    favoriteViewState: FavoriteViewState?,
    notification: UiNotification?,
    onRefresh: () -> Unit,
    navToDetail: (movieId) -> Unit,
    modifier: Modifier
) {

    var showNotificationDialog by remember { mutableStateOf(false) }

    LaunchedEffect(notification) {
        if (notification != null) {
            showNotificationDialog = true
        }
    }

    FavoriteScreenContent(
        viewState = favoriteViewState,
        onRefresh = onRefresh,
        toDetail = navToDetail,
        modifier = modifier
    )

    if (showNotificationDialog) {
        KinoUiNotification(
            notification = notification!!,
            onDismiss = {
                showNotificationDialog = false
            })
    }
}