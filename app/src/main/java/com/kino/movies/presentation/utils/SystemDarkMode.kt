package com.kino.movies.presentation.utils

import android.content.Context
import android.content.res.Configuration

class SystemThemeUtils(
    private val context: Context
) {
    fun isSystemInDarkMode(): Boolean {
        val uiMode = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return uiMode == Configuration.UI_MODE_NIGHT_YES
    }
}
