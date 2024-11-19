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

    fun getFavoriteMovies(): Flow<List<MovieEntity>> {
        return movieDao.getFavoriteMovies()
    }

    suspend fun upsertMovies(movies: List<MovieEntity>): Result<Unit> {
        return try {
            movies.forEach { movie ->
                movieDao.upsertMovie(movie)
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(Exception("Erreur lors de l'ajout des films: ${e.localizedMessage}"))
        }
    }

    suspend fun deleteMovie(movie: MovieEntity): Result<Unit> {
        return try {
            movieDao.deleteMovie(movie)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(Exception("Erreur lors de la suppression du film : ${e.localizedMessage}"))
        }
    }

    fun getMovieById(id: String): Flow<MovieEntity?> {
        return movieDao.getMovieById(id)
    }

    suspend fun deleteAllMovies(): Result<Unit> {
        return try {
            movieDao.deleteAllMovies()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(Exception("Erreur lors de la suppression des films: ${e.localizedMessage}"))
        }
    }

    suspend fun addMovieFavorite(movieId: String) {
        try {
            movieDao.setMovieFavorite(movieId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(Exception("Erreur lors de l'ajout du film aux favoris: ${e.localizedMessage}"))
        }
    }

    suspend fun removeMovieFavorite(movieId: String) {
        try {
            movieDao.removeMovieFavorite(movieId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(Exception("Erreur lors de la suppression du film des favoris: ${e.localizedMessage}"))
        }
    }
}