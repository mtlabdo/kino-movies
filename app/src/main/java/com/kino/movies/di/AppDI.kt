package com.kino.movies.di

import com.kino.movies.presentation.utils.SystemThemeUtils
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val appModules = module {

    single(named("IODispatcher")) {
        Dispatchers.IO
    }

    single(named("MainDispatcher")) {
        Dispatchers.Main
    }

    singleOf(::SystemThemeUtils)
}

val appDI = appModules
