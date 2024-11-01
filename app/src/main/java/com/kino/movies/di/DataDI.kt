package com.kino.movies.di

import com.kino.movies.data.local.datasource.MovieLocalDataSource
import com.kino.movies.data.network.datasource.MovieNetworkDataSource
import com.kino.movies.data.network.ktor.KtorHttpClient
import com.kino.movies.data.repository.MovieRepository
import com.kino.movies.domain.repository.IMovieRepository
import io.ktor.client.HttpClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


internal val ktorModules = module {
    single<HttpClient> { KtorHttpClient.build() }
}

internal val repositoryModules = module {

    single { MovieLocalDataSource() }
    single { MovieNetworkDataSource(get()) }
    singleOf(::MovieRepository) bind IMovieRepository::class
}


val dataDI = ktorModules + repositoryModules


