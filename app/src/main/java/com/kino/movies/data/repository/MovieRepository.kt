package com.kino.movies.data.repository

import com.kino.movies.R
import com.kino.movies.data.local.datasource.MovieLocalDataSource
import com.kino.movies.data.local.entity.MovieEntity
import com.kino.movies.data.mapper.toMovie
import com.kino.movies.data.mapper.toMovieEntity
import com.kino.movies.data.network.datasource.MovieNetworkDataSource
import com.kino.movies.data.network.dto.MovieDto
import com.kino.movies.domain.model.Movie
import com.kino.movies.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import com.kino.movies.domain.Result
import com.kino.movies.domain.doIfError
import com.kino.movies.domain.doIfSuccess
import com.kino.movies.presentation.utils.UIText

class MovieRepository(
    private val networkDataSource: MovieNetworkDataSource,
    private val localDataSource: MovieLocalDataSource
) : IMovieRepository {

    override fun getMovies(query: String?) = flow<Result<List<Movie>>> {

        val networkResult = networkDataSource.getMovies()
        networkResult.doIfSuccess { moviesNetwork ->
            localDataSource.upsertMovies(moviesNetwork.map(MovieDto::toMovieEntity))
        }
        networkResult.doIfError { error ->
            emit(error)
        }

        emitAll(localDataSource.getAllMovies()
            .map { movieEntities ->
                Result.Success(movieEntities.map(MovieEntity::toMovie))
            }
        )
    }.catch { e ->
        val message = if (e.cause?.message.isNullOrEmpty()) {
            UIText.DynamicString(e.cause?.message!!)
        } else {
            UIText.StringResource(R.string.error_inattendue)
        }
        emit(
            Result.Error(message = message, exception = e)
        )
    }

    override fun getMovie(id: String) = flow<Result<Movie>> {
        emitAll(localDataSource.getMovieById(id).map {
            if (it != null) {
                Result.Success(it.toMovie())
            } else {
                Result.Error(message = UIText.StringResource(R.string.error_get_movie_detail))
            }
        })
    }.catch { e ->
        val message = if (e.cause?.message.isNullOrEmpty()) {
            UIText.DynamicString(e.cause?.message!!)
        } else {
            UIText.StringResource(R.string.error_inattendue)
        }
        emit(
            Result.Error(message = message, exception = e)
        )
    }

    override suspend fun addMovieFavorite(movieId: String) {
        localDataSource.addMovieFavorite(movieId)
    }

    override suspend fun removeMovieFavorite(movieId: String) {
        localDataSource.removeMovieFavorite(movieId)
    }

    override fun getFavoriteMovies(): Flow<Result<List<Movie>>> = flow {
        emitAll(localDataSource.getFavoriteMovies()
            .map { movieEntities ->
                Result.Success(movieEntities.map(MovieEntity::toMovie))
            }
        )
    }
}