package com.kino.movies.domain.usecase.apptheme

import com.kino.movies.domain.repository.IUserPreferencesRepository

class GetAppThemeUseCase(
    private val userPreferencesRepository: IUserPreferencesRepository
) {
    operator fun invoke() = userPreferencesRepository.getTheme()
}