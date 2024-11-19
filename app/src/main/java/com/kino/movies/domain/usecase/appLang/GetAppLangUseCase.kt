package com.kino.movies.domain.usecase.appLang

import com.kino.movies.domain.repository.IUserPreferencesRepository

class GetAppLangUseCase(
    private val userPreferencesRepository: IUserPreferencesRepository
) {
    operator fun invoke() = userPreferencesRepository.getLanguage()
}