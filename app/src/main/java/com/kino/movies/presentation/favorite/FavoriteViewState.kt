package com.kino.movies.presentation.favorite

import com.kino.movies.domain.model.Movie

sealed class FavoriteViewState {
    object Loading : FavoriteViewState()
    data class FavoriteMoviesReady(val favoriteMovies: List<Movie>) : FavoriteViewState()
}