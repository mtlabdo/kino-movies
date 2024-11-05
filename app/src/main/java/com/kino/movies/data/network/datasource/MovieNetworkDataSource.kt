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
                            title = "The Penguin",
                            description = "Series streaming on Max\n" + "Picking up right after The Batman, where The Riddler nearly made Gotham the new Atlantis, Oswald Cobblepot, aka The Penguin, looks to take advantage of the chaos and establish himself as the city's new boss. Colin Farrell returns to the role with his layers of make-up and prosthetics, and he'll be challenged by Sofia Falcone, the daughter of Cobblepot's deceased former boss, Carmine Falcone. Cristin Milioti (Palm Springs, “Fargo”) plays Falcone in the next chapter of the DC Elseworlds universe, the franchise that lives independently from the universe James Gunn is building.",
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
                        ),
                        MovieDto(
                            id = "4",
                            title = "Movie 4",
                            description = "Description of Movie 4",
                            image = "https://m.media-amazon.com/images/M/MV5BMTMxMjU0MTMxMl5BMl5BanBnXkFtZTcwNjY4Mjc3MQ@@._V1_.jpg",
                            big_image = "https://example.com/movie4_big.jpg",
                            genre = listOf("Thriller"),
                            thumbnail = "https://m.media-amazon.com/images/M/MV5BMTMxMjU0MTMxMl5BMl5BanBnXkFtZTcwNjY4Mjc3MQ@@._V1_.jpg",
                            rating = "8.5",
                            rank = 4,
                            year = 2022,
                            imdbid = "tt4567890",
                            imdb_link = "https://www.imdb.com/title/tt4567890/"

                        ),

                        MovieDto(
                            id = "5",
                            title = "Movie 5",
                            description = "Description of Movie 5",
                            image = "https://m.media-amazon.com/images/M/MV5BMTMxMjU0MTMxMl5BMl5BanBnXkFtZTcwNjY4Mjc3MQ@@._V1_.jpg",
                            big_image = "https://example.com/movie5_big.jpg",
                            genre = listOf("Romance"),
                            thumbnail = "https://m.media-amazon.com/images/M/MV5BMTMxMjU0MTMxMl5BMl5BanBnXkFtZTcwNjY4Mjc3MQ@@._V1_.jpg",
                            rating = "7",
                            rank = 5,
                            year = 2021,
                            imdbid = "tt5678901",
                            imdb_link = "https://www.imdb.com/title/tt5678901/"
                        ),
                        MovieDto(
                            id = "6",
                            title = "Movie 6",
                            description = "Description of Movie 6",
                            image = "https://m.media-amazon.com/images/M/MV5BMTMxMjU0MTMxMl5BMl5BanBnXkFtZTcwNjY4Mjc3MQ@@._V1_.jpg",
                            big_image = "https://example.com/movie6_big.jpg",
                            genre = listOf("Sci-Fi"),
                            thumbnail = "https://m.media-amazon.com/images/M/MV5BMTMxMjU0MTMxMl5BMl5BanBnXkFtZTcwNjY4Mjc3MQ@@._V1_.jpg",
                            rating = "6.7",
                            rank = 6,
                            year = 2020,
                            imdbid = "tt6789012",
                            imdb_link = "https://www.imdb.com/title/tt6789012/"

                        ),
                        MovieDto(
                            id = "7",
                            title = "Movie 7",
                            description = "Description of Movie 7",
                            image = "https://m.media-amazon.com/images/M/MV5BMTMxMjU0MTMxMl5BMl5BanBnXkFtZTcwNjY4Mjc3MQ@@._V1_.jpg",
                            big_image = "",
                            genre = listOf("Animation"),
                            thumbnail = "https://m.media-amazon.com/images/M/MV5BMTMxMjU0MTMxMl5BMl5BanBnXkFtZTcwNjY4Mjc3MQ@@._V1_.jpg",
                            rating = "8.2",
                            rank = 7,
                            year = 2019,
                            imdbid = "tt7890123",
                            imdb_link = "https://www.imdb.com/title/tt7890123/"


                        ),
                        MovieDto(
                            id = "8",
                            title = "Movie 8",
                            description = "Description of Movie 8",
                            image = "https://m.media-amazon.com/images/M/MV5BMTMxMjU0MTMxMl5BMl5BanBnXkFtZTcwNjY4Mjc3MQ@@._V1_.jpg",
                            big_image = "",
                            genre = listOf("Horror"),
                            thumbnail = "https://m.media-amazon.com/images/M/MV5BMTMxMjU0MTMxMl5BMl5BanBnXkFtZTcwNjY4Mjc3MQ@@._V1_.jpg",
                            rating = "7.9",
                            rank = 8,
                            year = 2018,
                            imdbid = "tt8901234",
                            imdb_link = "https://www.imdb.com/title/tt8901234/"
                        ),
                        MovieDto(
                            id = "9",
                            title = "Movie 9",
                            description = "Description of Movie 9",
                            image = "https://m.media-amazon.com/images/M/MV5BMTMxMjU0MTMxMl5BMl5BanBnXkFtZTcwNjY4Mjc3MQ@@._V1_.jpg",
                            big_image = "",
                            genre = listOf("Mystery"),
                            thumbnail = "https://m.media-amazon.com/images/M/MV5BMTMxMjU0MTMxMl5BMl5BanBnXkFtZTcwNjY4Mjc3MQ@@._V1_.jpg",
                            rating = "8.1",
                            rank = 9,
                            year = 2017,
                            imdbid = "tt9012345",
                            imdb_link = "https://www.imdb.com/title/tt9012345/"
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