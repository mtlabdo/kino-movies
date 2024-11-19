package com.kino.movies.di

import com.kino.movies.presentation.detail.DetailViewModel
import com.kino.movies.presentation.favorite.FavoriteViewModel
import com.kino.movies.presentation.home.HomeViewModel
import com.kino.movies.presentation.setting.SettingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val viewModelModules = module {
    viewModel {
        HomeViewModel(get(), get(named("IODispatcher")))
    }
    viewModel {
        DetailViewModel(get(), get(), get(), get(named("IODispatcher")))
    }
    viewModel {
        FavoriteViewModel(get(), get(named("IODispatcher")))
    }
    viewModel {
        SettingViewModel(get(), get(), get(), get(), get(named("IODispatcher")))
    }
}

val presentationDI = viewModelModules




