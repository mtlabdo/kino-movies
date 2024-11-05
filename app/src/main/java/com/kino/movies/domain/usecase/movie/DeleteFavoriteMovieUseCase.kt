package com.kino.movies.domain.usecase.movie

import com.kino.movies.domain.repository.IMovieRepository

class DeleteFavoriteMovieUseCase(
    private val movieRepository: IMovieRepository
) {
    suspend operator fun invoke(movieId: String) = movieRepository.removeMovieFavorite(movieId)
}