package com.kino.movies.data.network.datasource

import com.kino.movies.data.network.dto.MovieDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import java.io.IOException

class MovieNetworkDataSource(private val httpClient: HttpClient) {

    suspend fun getMovies(): Result<List<MovieDto>> {
        return try {
            val response: HttpResponse = httpClient.get {
                method = HttpMethod.Get
            }

            when (response.status.value) {
                in 200..299  -> {
                    val moviesNetwork: List<MovieDto> = response.body()
                    Result.success(moviesNetwork)
                }
                in 400..499 -> {
                    Result.failure(Exception("Une erreur client s'est produite, code d'erreur: ${response.status.value}"))
                }
                in 500..599 -> {
                    Result.failure(Exception(" Une erreur serveur s'est produite, code d'erreur: ${response.status.value}"))
                }
                else -> {
                    Result.failure(Exception("Une erreur de communication avec le serveur s'est produite, code d'erreur : ${response.status.value}"))
                }
            }
        } catch (e: IOException) {
            Result.failure(Exception(" Une erreur de réseau s'est produite: ${e.localizedMessage} "))
        } catch (e: Exception) {
            Result.failure(Exception("Une erreur inattendue s'est produite: ${e.localizedMessage}"))
        }
    }
}