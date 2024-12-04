package com.kino.movies.data.network.utils

import io.ktor.http.HttpStatusCode

private const val FIRST_CLIENT_ERROR_CODE = 400
private const val LAST_CLIENT_ERROR_CODE = 499

private const val FIRST_SERVER_ERROR_CODE = 500
private const val LAST_SERVER_ERROR_CODE = 599

fun HttpStatusCode.isClientError() = this.value in  FIRST_CLIENT_ERROR_CODE..LAST_CLIENT_ERROR_CODE
fun HttpStatusCode.isServerError() = this.value in FIRST_SERVER_ERROR_CODE..LAST_SERVER_ERROR_CODE