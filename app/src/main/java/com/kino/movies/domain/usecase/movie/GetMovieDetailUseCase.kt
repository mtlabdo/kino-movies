package com.kino.movies.domain.usecase.movie

import com.kino.movies.domain.repository.IMovieRepository

class GetMovieDetailUseCase(
    private val movieRepository: IMovieRepository
) {
    operator fun invoke(id: String) = movieRepository.getMovie(id)
}