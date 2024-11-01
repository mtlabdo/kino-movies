package com.kino.movies.presentation.error

import com.kino.movies.domain.Result

data class ErrorUi(
    val message: String? = null
)

fun <T : Any> Result<T>.mapToErrorUi(): ErrorUi? =
    if (this is Result.Error) ErrorUi(message = message) else null