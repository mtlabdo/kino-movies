package com.kino.movies.data.repository

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

class MovieRepository(
    private val networkDataSource: MovieNetworkDataSource,
    private val localDataSource: MovieLocalDataSource
) : IMovieRepository {

    override fun getMovies(query: String?) = flow<Result<List<Movie>>> {
        val networkResult = networkDataSource.getMovies()
        if (networkResult.isSuccess) {
            val moviesNetwork = networkResult.getOrNull()
            if (moviesNetwork != null) {
                localDataSource.upsertMovies(moviesNetwork.map(MovieDto::toMovieEntity))
            }

        } else {
            emit(
                Result.failure(
                    networkResult.exceptionOrNull()
                        ?: Exception("Une erreur s'est produite lors de la récupération des films depuis le réseau")
                )
            )
        }

        emitAll(localDataSource.getAllMovies()
            .map { movieEntities ->
                Result.success(movieEntities.map(MovieEntity::toMovie))
            }
        )
    }.catch { e ->
        emit(Result.failure(Exception("Une erreur s'est produite lors de la récupération des films: ${e.localizedMessage}")))
    }


    override fun getMovie(id: String): Flow<Result<Movie>> {
        TODO("Not yet implemented")
    }

    override suspend fun addMovieFavorite(movie: Movie) {
        TODO("Not yet implemented")
    }

    override suspend fun removeMovieFavorite(movie: Movie) {
        TODO("Not yet implemented")
    }

    override fun getFavoriteMovies(): Flow<Result<List<Movie>>> {
        TODO("Not yet implemented")
    }

}