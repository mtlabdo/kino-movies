package com.kino.movies.domain.repository

import com.kino.movies.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import com.kino.movies.domain.Result

interface IMovieRepository {

    /**
     * Get the list of movies with an optional query parameter (title of movie).
     */
     fun getMovies(query: String?): Flow<Result<List<Movie>>>

    /**
     * Get a movie by its id.
     */
    fun getMovie(id: String): Flow<Result<Movie>>

    /**
     * Add a movie to favorites.
     */
    suspend fun addMovieFavorite(movieId: String)

    suspend fun removeMovieFavorite(movieId: String)

    fun getFavoriteMovies(): Flow<Result<List<Movie>>>
}