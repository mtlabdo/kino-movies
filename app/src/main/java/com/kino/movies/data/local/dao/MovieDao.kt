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
     fun getMovieById(id: String): Flow<MovieEntity?>

    @Query("DELETE FROM movie")
    suspend fun deleteAllMovies()

    @Query("UPDATE movie SET favorite = 1 WHERE id = :movieId")
    suspend fun setMovieFavorite(movieId: String)

    @Query("UPDATE movie SET favorite = 0 WHERE id = :movieId")
    suspend fun removeMovieFavorite(movieId: String)



}