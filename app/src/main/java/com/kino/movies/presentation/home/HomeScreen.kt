package com.kino.movies.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kino.movies.presentation.utils.dummyFilms

@Composable
fun HomeScreen(
    homeViewState: HomeViewState?,
    onRefresh: () -> Unit,
    navToDetail: (movieId) -> Unit,
    modifier: Modifier = Modifier
) {
    HomeScreenContent(
        viewState = homeViewState,
        onRefresh = onRefresh,
        toDetail = navToDetail,
        modifier = modifier
    )
}

typealias movieId = String

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        homeViewState = HomeViewState.MoviesReady(dummyFilms),
        onRefresh = {},
        navToDetail = {}
    )
}