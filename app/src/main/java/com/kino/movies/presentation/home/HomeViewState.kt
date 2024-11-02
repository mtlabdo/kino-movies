package com.kino.movies.presentation.home

import com.kino.movies.domain.model.Movie

sealed class HomeViewState {
    object Loading : HomeViewState()
    data class MoviesReady(val movies: List<Movie>) : HomeViewState()
}