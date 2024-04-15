package com.example.movies

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MoviesApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MoviesApplication)
            androidLogger(Level.DEBUG)
            modules(listOf(
                repositoryModule,
                viewModelModule,
                serviceModule
            ))
        }
    }
}