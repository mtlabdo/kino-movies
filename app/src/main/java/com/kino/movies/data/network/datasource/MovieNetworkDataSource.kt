package com.kino.movies.data.network.datasource

import android.util.Log
import com.kino.movies.data.network.DISCOVER_MOVIE_API
import com.kino.movies.data.network.dto.ApiResponse
import com.kino.movies.data.network.dto.MovieDto
import com.kino.movies.data.network.dto._MovieDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import java.io.IOException
import com.kino.movies.domain.Result
import io.ktor.http.HttpStatusCode

class MovieNetworkDataSource(private val httpClient: HttpClient) {

    suspend fun getMovies(): Result<List<_MovieDto>> {
        return try {
            val response: HttpResponse = httpClient.get(DISCOVER_MOVIE_API)

            val resValue = HttpStatusCode.OK.value

            when (response.status.value) {
                //when (resValue) {
                in 200..299 -> {
                    val moviesNetwork: ApiResponse<_MovieDto> = response.body()
                    Result.Success(moviesNetwork.results)
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