package com.kino.movies.data.network.ktor

import com.kino.movies.BuildConfig
import com.kino.movies.data.network.API_HOST
import com.kino.movies.data.network.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorHttpClient {

    fun build(): HttpClient {
        return HttpClient {
            installContentNegotiation()
            installLogging()
            expectSuccess = true
            installResponseValidation()
            install(DefaultRequest) {
                url(BASE_URL)
                headers.append("x-rapidapi-host", API_HOST)
                headers.append("x-rapidapi-key", BuildConfig.API_KEY)
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

    private fun HttpClientConfig<*>.installResponseValidation() {
        HttpResponseValidator {
            handleResponseExceptionWithRequest { exception, request ->
                println("HTTP error for ${request.url}: $exception")
            }
        }
    }
}
