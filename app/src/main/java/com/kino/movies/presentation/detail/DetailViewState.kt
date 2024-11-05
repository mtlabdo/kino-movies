package com.kino.movies.presentation.detail

import com.kino.movies.domain.model.Movie

sealed class DetailViewState {
    object Loading : DetailViewState()
    data class MovieDetailReady(val movieDetail: Movie) : DetailViewState()
}