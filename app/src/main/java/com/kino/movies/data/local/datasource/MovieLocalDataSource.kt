package com.kino.movies.data.local.datasource

import com.kino.movies.data.local.dao.MovieDao
import com.kino.movies.data.local.entity.MovieEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

// TODO IMPLEMENT DATABASE
class MovieLocalDataSource() {

    private val moviesList = mutableListOf<MovieEntity>()

    val movies  = flow<List<MovieEntity>> {
        emit(moviesList)
    }

    private val _moviesFlow = MutableStateFlow<List<MovieEntity>>(emptyList())

    init {
        CoroutineScope(Dispatchers.IO).launch {
  /*          delay(10000)
            val movie = _moviesFlow.value.get(0)
            val movie3 = _moviesFlow.value.get(3)
            deleteMovie(movie)
            delay(10000)
            deleteMovie(movie3)*/
        }
    }
    fun getAllMovies(): Flow<List<MovieEntity>> {
        //return movieDao.getAllMovies()
        return flow {
            emitAll(_moviesFlow)
        }
    }


    suspend fun upsertMovies(movies: List<MovieEntity>): Result<Unit> {
        _moviesFlow.emit(movies)
        return Result.success(Unit)
       /* return try {
            movies.forEach { movie ->
                movieDao.upsertMovie(movie)
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(Exception("Failed to insert movie into local database: ${e.localizedMessage}"))
        }*/
    }

    suspend fun deleteMovie(movie: MovieEntity): Result<Unit> {
        _moviesFlow.value = _moviesFlow.value.filter {
            it.id != movie.id
        }
        return Result.success(Unit)
        /*       return try {
            movieDao.deleteMovie(movie)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(Exception("Failed to delete movie from local database: ${e.localizedMessage}"))
        }*/
    }

/*    suspend fun getMovieById(id: Int): Result<MovieEntity?> {
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
    }*/
}