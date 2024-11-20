package com.kino.movies.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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