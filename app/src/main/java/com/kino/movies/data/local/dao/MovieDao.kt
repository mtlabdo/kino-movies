package com.kino.movies.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.kino.movies.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Upsert
    suspend fun upsertMovie(movie: MovieEntity): Long

    @Delete
    suspend fun deleteMovie(movie: MovieEntity)

    @Query("SELECT * FROM movie WHERE id = :id")
    suspend fun getMovieById(id: String): MovieEntity?

    @Query("DELETE FROM movie")
    suspend fun deleteAllMovies()

}