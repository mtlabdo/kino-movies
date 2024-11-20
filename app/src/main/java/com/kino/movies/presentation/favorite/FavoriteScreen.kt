package com.kino.movies.presentation.favorite

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kino.movies.presentation.home.movieId

@Composable
fun FavoriteScreen(
    favoriteViewState: FavoriteViewState?,
    onRefresh: () -> Unit,
    navToDetail: (movieId) -> Unit,
    modifier: Modifier
) {
    FavoriteScreenContent(
        viewState = favoriteViewState,
        onRefresh = onRefresh,
        toDetail = navToDetail,
        modifier = modifier
    )
}