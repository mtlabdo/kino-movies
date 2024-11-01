package com.kino.movies.presentation.home

import com.kino.movies.domain.model.Movie

sealed class HomeState {
    object Loading : HomeState()
    data class Success(val movies: List<Movie>) : HomeState()
    data class Error(val message: String) : HomeState()
}