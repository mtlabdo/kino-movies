package com.kino.movies.domain.usecase.movie

import com.kino.movies.domain.repository.IMovieRepository

class GetMoviesUseCase(
    private val movieRepository: IMovieRepository
) {
    operator fun invoke(query : String?) = movieRepository.getMovies(query)
}