package com.kino.movies.presentation.detail

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.kino.movies.R
import com.kino.movies.presentation.KinoAnimateVisibility
import com.kino.movies.presentation.designsystem.components.KinoTopBar
import com.kino.movies.presentation.designsystem.components.KinoUiNotification
import com.kino.movies.presentation.utils.UiNotification

@Composable
fun DetailScreen(
    viewState: DetailViewState?,
    notification: UiNotification?,
    onBack: () -> Unit,
    onRefresh: () -> Unit = {},
    onUpdateFavorite: (newFavoriteState) -> Unit,
    modifier: Modifier = Modifier.padding(0.dp)
) {
    val context = LocalContext.current
    var showNotificationDialog by remember { mutableStateOf(false) }

    LaunchedEffect(notification) {
        if (notification != null) {
            showNotificationDialog = true
        }
    }
    DetailScreenContent(
        viewState = viewState,
        onRefresh = onRefresh,
        onUpdateFavorite = onUpdateFavorite,
        modifier = modifier
    )

    Scaffold(
        topBar = {
            KinoAnimateVisibility(true) {
                KinoTopBar(context.resources.getString(R.string.detail),
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null
                            )
                        }
                    }
                )
            }
        },
    ) { padding ->
        DetailScreenContent(
            viewState = viewState,
            onRefresh = onRefresh,
            onUpdateFavorite = onUpdateFavorite,
            modifier = modifier.padding(padding)
        )
    }

    if (showNotificationDialog) {
        KinoUiNotification(
            notification = notification!!,
            onDismiss = {
                showNotificationDialog = false
            })
    }
}
