package com.kino.movies.domain.usecase.appLang

import com.kino.movies.domain.model.AppLanguage
import com.kino.movies.domain.repository.IUserPreferencesRepository

class SetAppLangUseCase(
    private val userPreferencesRepository: IUserPreferencesRepository
) {
    suspend operator fun invoke(
        language: AppLanguage
    ) = userPreferencesRepository.setLanguage(language)
}