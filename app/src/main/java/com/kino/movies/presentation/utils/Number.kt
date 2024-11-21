package com.kino.movies.presentation.utils

internal fun Int.format(digits: Int): String = "%0${digits}d".format(this)

internal fun Double.format(digits: Int = 1) = "%.${digits}f".format(this)
