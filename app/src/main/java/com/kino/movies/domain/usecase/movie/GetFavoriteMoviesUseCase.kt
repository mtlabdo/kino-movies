package com.kino.movies.domain.usecase.movie

import com.kino.movies.domain.repository.IMovieRepository

class GetFavoriteMoviesUseCase(
    private val movieRepository: IMovieRepository
) {
     operator fun invoke() = movieRepository.getFavoriteMovies()
}