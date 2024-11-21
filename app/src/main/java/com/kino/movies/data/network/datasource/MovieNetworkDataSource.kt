package com.kino.movies.data.network.datasource

import com.kino.movies.R
import com.kino.movies.data.network.DISCOVER_MOVIE_API
import com.kino.movies.data.network.dto.ApiResponse
import com.kino.movies.data.network.dto.MovieDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import java.io.IOException
import com.kino.movies.domain.Result
import com.kino.movies.presentation.utils.UIText

class MovieNetworkDataSource(private val httpClient: HttpClient) {

    suspend fun getMovies(): Result<List<MovieDto>> {
        return try {
            val response: HttpResponse = httpClient.get(DISCOVER_MOVIE_API)

            when (response.status.value) {
                in 200..299 -> {
                    val moviesNetwork: ApiResponse<MovieDto> = response.body()
                    Result.Success(moviesNetwork.results)
                }

                in 400..499 -> {
                    Result.Error(
                        code = response.status.value,
                        message = UIText.StringResource(R.string.error_client)
                    )
                }

                in 500..599 -> {
                    Result.Error(
                        code = response.status.value,
                        message = UIText.StringResource(R.string.error_server)
                    )
                }

                else -> {
                    Result.Error(
                        code = response.status.value,
                        message = UIText.StringResource(R.string.error_communication)
                    )
                }
            }
        } catch (e: IOException) {
            Result.Error(message = UIText.StringResource(R.string.error_network))
        } catch (e: Exception) {
            Result.Error(message = UIText.StringResource(R.string.error_unknown))
        }
    }
}