package com.kino.movies.data.network.datasource

import android.util.Log
import com.kino.movies.data.network.dto.MovieDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import java.io.IOException
import com.kino.movies.domain.Result
import io.ktor.http.HttpStatusCode

class MovieNetworkDataSource(private val httpClient: HttpClient) {

    suspend fun getMovies(): Result<List<MovieDto>> {
        return try {
            val response: HttpResponse = httpClient.get {
                method = HttpMethod.Get
            }

            val resValue = HttpStatusCode.OK.value

           // when (response.status.value) {
            when (resValue) {
                in 200..299 -> {
                    val moviesNetwork = listOf(
                        MovieDto(
                            id = "1",
                            title = "Movie 1",
                            description = "Description of Movie 1",
                            image = "https://m.media-amazon.com/images/M/MV5BMTMxMjU0MTMxMl5BMl5BanBnXkFtZTcwNjY4Mjc3MQ@@._V1_.jpg",
                            big_image = "https://example.com/movie1_big.jpg",
                            genre = listOf("Action", "Adventure"),
                            thumbnail = "https://m.media-amazon.com/images/M/MV5BMTMxMjU0MTMxMl5BMl5BanBnXkFtZTcwNjY4Mjc3MQ@@._V1_.jpg",
                            rating = "8.5",
                            rank = 1,
                            year = 2022,
                            imdbid = "tt1234567",
                            imdb_link = "https://www.imdb.com/title/tt1234567/"
                        ),
                        MovieDto(
                            id = "2",
                            title = "Movie 2",
                            description = "Description of Movie 2",
                            image = "https://m.media-amazon.com/images/M/MV5BMTMxMjU0MTMxMl5BMl5BanBnXkFtZTcwNjY4Mjc3MQ@@._V1_.jpg",
                            big_image = "https://example.com/movie2_big.jpg",
                            genre = listOf("Drama"),
                            thumbnail = "https://m.media-amazon.com/images/M/MV5BMTMxMjU0MTMxMl5BMl5BanBnXkFtZTcwNjY4Mjc3MQ@@._V1_.jpg",
                            rating = "7.8",
                            rank = 2,
                            year = 20,
                            imdbid = "tt2345678",
                            imdb_link = "https://www.imdb.com/title/tt2345678/"
                        ),
                        MovieDto(
                            id = "3",
                            title = "Movie 3",
                            description = "Description of Movie 3",
                            image = "https://m.media-amazon.com/images/M/MV5BMTMxMjU0MTMxMl5BMl5BanBnXkFtZTcwNjY4Mjc3MQ@@._V1_.jpg",
                            big_image = "https://example.com/movie3_big.jpg",
                            genre = listOf("Comedy"),
                            thumbnail = "https://m.media-amazon.com/images/M/MV5BMTMxMjU0MTMxMl5BMl5BanBnXkFtZTcwNjY4Mjc3MQ@@._V1_.jpg",
                            rating = "6.4",
                            rank = 3,
                            year = 2023,
                            imdbid = "tt3456789",
                            imdb_link = "https://www.imdb.com/title/tt3456789/"
                        )
                    )

                    Result.Success(moviesNetwork)
                }

                in 400..499 -> {
                    Result.Error(
                        code = response.status.value,
                        message = "Une erreur client s'est produite lors la communication avec le serveur"
                    )
                }

                in 500..599 -> {
                    Result.Error(
                        code = response.status.value,
                        message = "Une erreur serveur s'est produite lors la récupération des films"
                    )
                }

                else -> {
                    Result.Error(
                        code = response.status.value,
                        message = "Une erreur de communication avec le serveur s'est produite"
                    )
                }
            }
        } catch (e: IOException) {
            Result.Error(message = "Une erreur de réseau s'est produite")
        } catch (e: Exception) {
            Result.Error(message = "Une erreur inattendue s'est produite lors de la communication avec le serveur")
        }
    }
}