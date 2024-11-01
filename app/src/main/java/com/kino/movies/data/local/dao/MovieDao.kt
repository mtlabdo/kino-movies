package com.kino.movies.data.local.dao

import com.kino.movies.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieDao {

    //@Query("SELECT * FROM movies")
    fun getAllMovies(): Flow<List<MovieEntity>>

    suspend fun upsertMovie(movie: MovieEntity): Long

    suspend fun deleteMovie(movie: MovieEntity): Long

    suspend fun getMovieById(id: Int): MovieEntity?

    suspend fun deleteAllMovies()

}