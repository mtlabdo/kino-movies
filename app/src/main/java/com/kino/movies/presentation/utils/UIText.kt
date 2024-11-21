package com.kino.movies.presentation.utils

import android.content.Context
import androidx.annotation.StringRes

sealed class UIText {
    data class DynamicString(
        val value: String,
    ) : UIText()

    data class StringResource(
        @StringRes val id: Int,
    ) : UIText()
}

fun UIText.getString(context: Context): String =
    when (this) {
        is UIText.DynamicString -> this.value
        is UIText.StringResource -> context.getString(this.id)
    }
