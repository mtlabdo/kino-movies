package com.kino.movies.presentation.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


/**
 * Return date in format: HH:mm.
 */
fun Long.timeFormat(): String {
    val localDateTime: LocalDateTime = this.toLocalDateTime()

    return "${localDateTime.hour.format(2)}:${localDateTime.minute.format(2)}h"
}

/**
 * Return date in format: dd-MM-yyyy.
 */
fun Long.dateFormat(): String {
    val localDateTime: LocalDateTime = this.toLocalDateTime()

    return "${localDateTime.dayOfMonth.format(2)}-${localDateTime.monthNumber.format(2)}-${localDateTime.year}"
}

fun Long.localTime(): LocalTime = this.toLocalDateTime().time

/**
 *  Return date in format: dd-MM-yyyy HH:mm.
 *  Output example : 01-11-2024 00:00
 */
fun Long.dateTimeFormat(): String {
    val localDateTime: LocalDateTime = this.toLocalDateTime()
    return "${localDateTime.dayOfMonth.format(2)}-${localDateTime.monthNumber.format(2)}-${localDateTime.year} " +
            "${localDateTime.hour.format(2)}:${localDateTime.minute.format(2)}"
}

internal fun Int.format(digits: Int): String = "%0${digits}d".format(this)


internal fun Long.toLocalDateTime(): LocalDateTime =
    Instant.fromEpochMilliseconds(this).toLocalDateTime(TimeZone.UTC)
