package com.kino.movies.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.kino.movies.data.local.dao.MovieDao
import com.kino.movies.data.local.database.DatabaseBuilder
import com.kino.movies.data.local.database.KinoAppDatabase
import com.kino.movies.data.local.datasource.MovieLocalDataSource
import com.kino.movies.data.network.datasource.MovieNetworkDataSource
import com.kino.movies.data.network.ktor.KtorHttpClient
import com.kino.movies.data.preferences.datastore
import com.kino.movies.data.repository.MovieRepository
import com.kino.movies.data.repository.UserPreferencesRepository
import com.kino.movies.domain.repository.IMovieRepository
import com.kino.movies.domain.repository.IUserPreferencesRepository
import io.ktor.client.HttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


internal val ktorModules = module {
    single<HttpClient> { KtorHttpClient.build() }
}

internal val databaseModules = module {
    single<KinoAppDatabase> {
        DatabaseBuilder(get()).buildDatabase()
    }
    single<MovieDao> { get<KinoAppDatabase>().movieDao() }
}

internal val preferenceModules = module {
    single<DataStore<Preferences>> {
        androidContext().datastore
    }
}

internal val repositoryModules = module {
    // Movie Repository
    singleOf(::MovieLocalDataSource)
    singleOf(::MovieNetworkDataSource)
    singleOf(::MovieRepository) bind IMovieRepository::class
    singleOf(::UserPreferencesRepository) bind IUserPreferencesRepository::class
}


val dataDI = ktorModules + repositoryModules + databaseModules + preferenceModules


