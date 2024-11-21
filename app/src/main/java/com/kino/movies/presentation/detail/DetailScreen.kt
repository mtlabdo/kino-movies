package com.kino.movies.presentation.detail

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kino.movies.R
import com.kino.movies.presentation.KinoAnimateVisibility
import com.kino.movies.presentation.designsystem.component.KinoTopBar
import com.kino.movies.presentation.utils.dummyFilms


@Composable
fun DetailScreen(
    viewState: DetailViewState?,
    onBack: () -> Unit,
    onRefresh: () -> Unit = {},
    onUpdateFavorite: (newFavoriteState) -> Unit,
    modifier: Modifier = Modifier.padding(0.dp)
) {
    val context = LocalContext.current

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

}


@Preview
@Composable
fun DetailScreenLoadingPreview() {
    DetailScreen(
        viewState = DetailViewState.Loading,
        onBack = {},
        onRefresh = {},
        onUpdateFavorite = {})

}

@Preview
@Composable
fun DetailScreenMoviePreview() {
    DetailScreen(
        viewState = DetailViewState.MovieDetailReady(
            movieDetail = dummyFilms[0]
        ),
        onBack = {},
        onRefresh = {},
        onUpdateFavorite = {})

}