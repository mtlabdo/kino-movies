package com.kino.movies.domain.usecase.movie

import com.kino.movies.domain.repository.IMovieRepository

class AddFavoriteMovieUseCase(
    private val movieRepository: IMovieRepository

) {
    suspend operator fun invoke(movieId: String) = movieRepository.addMovieFavorite(movieId)
}