package com.kino.movies.data.local.datasource

import com.kino.movies.data.local.dao.MovieDao
import com.kino.movies.data.local.entity.MovieEntity

import kotlinx.coroutines.flow.Flow

class MovieLocalDataSource(
    private val movieDao: MovieDao
) {

    fun getAllMovies(): Flow<List<MovieEntity>> {
        return movieDao.getAllMovies()
    }

    suspend fun upsertMovies(movies: List<MovieEntity>): Result<Unit> {
        return try {
            movies.forEach { movie ->
                movieDao.upsertMovie(movie)
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(Exception("Failed to insert movie into local database: ${e.localizedMessage}"))
        }
    }

    suspend fun deleteMovie(movie: MovieEntity): Result<Unit> {
        return try {
            movieDao.deleteMovie(movie)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(Exception("Failed to delete movie from local database: ${e.localizedMessage}"))
        }
    }

    suspend fun getMovieById(id: String): Result<MovieEntity?> {
        return try {
            val movie = movieDao.getMovieById(id)
            Result.success(movie)
        } catch (e: Exception) {
            Result.failure(Exception("Failed to retrieve movie by ID from local database: ${e.localizedMessage}"))
        }
    }

    suspend fun deleteAllMovies(): Result<Unit> {
        return try {
            movieDao.deleteAllMovies()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(Exception("Failed to delete all movies from local database: ${e.localizedMessage}"))
        }
    }
}