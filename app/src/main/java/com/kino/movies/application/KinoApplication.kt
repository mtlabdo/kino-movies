package com.kino.movies.application

import android.app.Application
import com.kino.movies.di.appDI
import com.kino.movies.di.dataDI
import com.kino.movies.di.domainDI
import com.kino.movies.di.presentationDI
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class KinoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@KinoApplication)
            modules(appDI + dataDI + domainDI + presentationDI)
        }
    }

}