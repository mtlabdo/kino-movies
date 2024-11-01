package com.kino.movies.domain.usecase.apptheme

import com.kino.movies.domain.model.AppTheme
import com.kino.movies.domain.repository.IUserPreferencesRepository

class SetAppThemeUseCase(
    private val userPreferencesRepository: IUserPreferencesRepository
) {
    suspend operator fun invoke(
        theme: AppTheme
    ) = userPreferencesRepository.setTheme(theme)
}