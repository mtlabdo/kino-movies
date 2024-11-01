package com.kino.movies.domain.usecase.movie

import com.kino.movies.domain.model.Movie
import com.kino.movies.domain.repository.IMovieRepository

class AddMovieFavoriteUseCase(
    private val movieRepository: IMovieRepository

) {
    suspend operator fun invoke(movie: Movie) = movieRepository.addMovieFavorite(movie)
}