package com.kino.movies.domain.repository

import com.kino.movies.domain.model.AppLanguage
import com.kino.movies.domain.model.AppTheme
import com.kino.movies.presentation.setting.Language
import kotlinx.coroutines.flow.Flow

interface IUserPreferencesRepository {

    /**
     * Get the user selected [AppTheme].
     */
    fun getTheme(): Flow<AppTheme>

    /**
     * Set the user selected [AppTheme].
     */
    suspend fun setTheme(theme: AppTheme)

    /**
     * Get the user selected [AppLanguage].
     */
    fun getLanguage(): Flow<String?>

    /**
     * Set the user selected [AppLanguage].
     */
    suspend fun setLanguage(language: String)
}