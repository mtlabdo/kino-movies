package com.kino.movies.presentation.favorite

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kino.movies.presentation.home.movieId
import com.kino.movies.presentation.utils.dummyFilms

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

@Preview
@Composable
fun FavoriteScreenPreview() {
    FavoriteScreen(
        favoriteViewState = FavoriteViewState.FavoriteMoviesReady(dummyFilms),
        onRefresh = {},
        navToDetail = {},
        modifier = Modifier
    )
}