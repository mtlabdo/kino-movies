package com.kino.movies.di

import com.kino.movies.domain.usecase.movie.GetMoviesUseCase
import com.kino.movies.domain.usecase.movie.GetMovieDetailUseCase
import com.kino.movies.domain.usecase.movie.AddFavoriteMovieUseCase
import com.kino.movies.domain.usecase.movie.GetFavoriteMoviesUseCase
import com.kino.movies.domain.usecase.movie.DeleteFavoriteMovieUseCase
import com.kino.movies.domain.usecase.apptheme.GetAppThemeUseCase
import com.kino.movies.domain.usecase.apptheme.SetAppThemeUseCase
import com.kino.movies.domain.usecase.appLang.GetAppLangUseCase
import com.kino.movies.domain.usecase.appLang.SetAppLangUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


internal val useCaseModules = module {
    singleOf(::GetMoviesUseCase)
    singleOf(::GetMovieDetailUseCase)
    singleOf(::GetFavoriteMoviesUseCase)
    singleOf(::AddFavoriteMovieUseCase)
    singleOf(::DeleteFavoriteMovieUseCase)
    singleOf(::GetAppThemeUseCase)
    singleOf(::SetAppThemeUseCase)
    singleOf(::GetAppLangUseCase)
    singleOf(::SetAppLangUseCase)
}

val domainDI = useCaseModules


