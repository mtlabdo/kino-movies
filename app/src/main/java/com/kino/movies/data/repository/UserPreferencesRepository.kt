package com.kino.movies.data.repository

import com.kino.movies.domain.model.AppLanguage
import com.kino.movies.domain.model.AppTheme
import com.kino.movies.domain.repository.IUserPreferencesRepository
import kotlinx.coroutines.flow.Flow

class UserPreferencesRepository : IUserPreferencesRepository {
    override fun getTheme(): Flow<AppTheme> {
        TODO("Not yet implemented")
    }

    override suspend fun setTheme(theme: AppTheme) {
        TODO("Not yet implemented")
    }

    override fun getLanguage(): Flow<AppLanguage> {
        TODO("Not yet implemented")
    }

    override suspend fun setLanguage(language: AppLanguage) {
        TODO("Not yet implemented")
    }
}