package com.kino.movies.data.preferences

import androidx.datastore.preferences.core.stringPreferencesKey

object UserPreferencesKeys{
    val USER_THEME = stringPreferencesKey("user_theme")
    val USER_LANGUAGE = stringPreferencesKey("user_language")
}