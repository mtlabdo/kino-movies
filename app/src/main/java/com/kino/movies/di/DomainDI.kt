package com.kino.movies.di

import com.kino.movies.domain.usecase.movie.GetMoviesUseCase
import org.koin.dsl.module


internal val useCaseModules = module {
        single {
            GetMoviesUseCase(get())
        }
}

val domainDI = useCaseModules


