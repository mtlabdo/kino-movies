package com.kino.movies.data.local.datasource

import android.database.SQLException
import com.kino.movies.R
import com.kino.movies.data.local.dao.MovieDao
import com.kino.movies.data.local.entity.MovieEntity

import kotlinx.coroutines.flow.Flow
import com.kino.movies.domain.Result
import com.kino.movies.presentation.utils.UIText

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
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(message = UIText.StringResource(R.string.error_save_movies), exception = e)
        }
    }

    suspend fun deleteMovie(movie: MovieEntity): Result<Unit> {
        return try {
            movieDao.deleteMovie(movie)
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(message = UIText.StringResource(R.string.error_delete_movie), exception = e)
        }
    }

    fun getMovieById(id: String): Flow<MovieEntity?> {
        return movieDao.getMovieById(id)
    }


    suspend fun addMovieFavorite(movieId: String) {
        try {
            movieDao.setMovieFavorite(movieId)
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(message = UIText.StringResource(R.string.error_add_fav_movie), exception = e)
        }
    }

    suspend fun removeMovieFavorite(movieId: String) {
        try {
            movieDao.removeMovieFavorite(movieId)
            Result.Success(Unit)
        } catch (e: SQLException) {
            Result.Error(message = UIText.StringResource(R.string.error_delete_movie), exception = e)
        }
    }
}
