package com.kino.movies.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.kino.movies.data.preferences.UserPreferencesKeys
import com.kino.movies.domain.model.AppLanguage
import com.kino.movies.domain.model.AppTheme
import com.kino.movies.domain.repository.IUserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>
) : IUserPreferencesRepository {
    override fun getTheme(): Flow<AppTheme> = dataStore.data.catch { exception ->
        when (exception) {
            // dataStore.data throws an IOException when an error is encountered when reading data
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

    override fun getLanguage(): Flow<AppLanguage> = dataStore.data.catch { exception ->
        when (exception) {
            // dataStore.data throws an IOException when an error is encountered when reading data
            is IOException -> emit(emptyPreferences())
            else -> throw exception
        }
    }.map { preferences ->
        val userLanguagePreference = preferences[UserPreferencesKeys.USER_LANGUAGE]
        AppLanguage.entries.find { it.name == userLanguagePreference } ?: AppLanguage.ENGLISH
    }

    override suspend fun setLanguage(language: AppLanguage) {
        dataStore.edit { preferences ->
            preferences[UserPreferencesKeys.USER_LANGUAGE] = language.name
        }
    }
}