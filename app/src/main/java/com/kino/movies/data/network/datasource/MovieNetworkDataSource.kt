package com.kino.movies.data.network.datasource

import com.kino.movies.R
import com.kino.movies.data.network.DISCOVER_MOVIE_API
import com.kino.movies.data.network.dto.ApiResponse
import com.kino.movies.data.network.dto.MovieDto
import com.kino.movies.data.network.utils.isClientError
import com.kino.movies.data.network.utils.isServerError
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import java.io.IOException
import com.kino.movies.domain.Result
import com.kino.movies.presentation.utils.UIText
import io.ktor.http.isSuccess

class MovieNetworkDataSource(private val httpClient: HttpClient) {

    suspend fun getMovies(): Result<List<MovieDto>> {
        return try {
            val response: HttpResponse = httpClient.get(DISCOVER_MOVIE_API)

            when {
                response.status.isSuccess() -> {
                    val moviesNetwork: ApiResponse<MovieDto> = response.body()
                    Result.Success(moviesNetwork.results)
                }

                response.status.isClientError() -> {
                    Result.Error(
                        code = response.status.value,
                        message = UIText.StringResource(R.string.error_client)
                    )
                }

                response.status.isServerError() -> {
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
            Result.Error(message = UIText.StringResource(R.string.error_network), exception = e)
        } catch (e: Exception) {
            Result.Error(message = UIText.StringResource(R.string.error_unknown), exception = e)
        }
    }
}