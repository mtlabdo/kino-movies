package com.kino.movies.domain.repository

import com.kino.movies.domain.model.AppTheme
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
}