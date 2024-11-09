package com.kino.movies.data.network.ktor

import com.kino.movies.data.network.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorHttpClient {

    fun build(): HttpClient {
        return HttpClient {
            installContentNegotiation()
            installLogging()
            install(DefaultRequest) {
                url(BASE_URL)

                header(
                    "Authorization",
                    "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlZjA5OTVmMzM4MTYxN2FhNDA1Y2UxYzE1MzQ4ZmZjMCIsIm5iZiI6MTczMTE4NzQ1OS42MjM1MDIzLCJzdWIiOiI1YzcwMGJjNGMzYTM2ODVhMmVkNTNhZjUiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.JJdeNVKKAUn3shtbTykNQjM-RQZT9V7zeBeI9qU8Zcs"
                )
            }
        }
    }

    private fun HttpClientConfig<*>.installContentNegotiation() {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    private fun HttpClientConfig<*>.installLogging() {
        install(Logging) {
            logger = Logger.ANDROID
            level = LogLevel.INFO
        }
    }
}
