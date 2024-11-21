package com.kino.movies.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.kino.movies.data.preferences.UserPreferencesKeys
import com.kino.movies.domain.model.AppTheme
import com.kino.movies.domain.repository.IUserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>,
) : IUserPreferencesRepository {
    override fun getTheme(): Flow<AppTheme> = dataStore.data.catch { exception ->
        when (exception) {
            is IOException -> emit(emptyPreferences())
            else -> throw exception
        }
    }.map { preferences ->
        val userThemePreference = preferences[UserPreferencesKeys.USER_THEME]
        AppTheme.entries.find { it.name == userThemePreference } ?: AppTheme.FOLLOW_SYSTEM
    }

    override suspend fun setTheme(theme: AppTheme) {
        dataStore.edit { preferences ->
            preferences[UserPreferencesKeys.USER_THEME] = theme.name
        }
    }

    override fun getLanguage(): Flow<String?> = dataStore.data.catch { exception ->
        when (exception) {
            is IOException -> emit(emptyPreferences())
            else -> throw exception
        }
    }.map { preferences ->
        preferences[UserPreferencesKeys.USER_LANGUAGE]
    }

    override suspend fun setLanguage(localCode: String) {
        dataStore.edit { preferences ->
            preferences[UserPreferencesKeys.USER_LANGUAGE] = localCode
        }
    }
}