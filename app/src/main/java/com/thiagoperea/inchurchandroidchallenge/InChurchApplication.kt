package com.thiagoperea.inchurchandroidchallenge

import android.app.Application
import com.thiagoperea.inchurchandroidchallenge.di.KoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class InChurchApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@InChurchApplication)
            modules(KoinModules.getModules())
        }
    }
}