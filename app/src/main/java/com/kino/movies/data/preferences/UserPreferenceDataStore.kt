package com.kino.movies.data.preferences

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

private const val USER_PREFERENCES_NAME = "user_preferences"

val Context.datastore by preferencesDataStore(USER_PREFERENCES_NAME)