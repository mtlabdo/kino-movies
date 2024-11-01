package com.kino.movies.di

import com.kino.movies.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val viewModelModules = module {
    viewModel {
        HomeViewModel(get(), get(named("IODispatcher")))
    }
}

val presentationDI = viewModelModules




