package com.thiagoperea.inchurchandroidchallenge.presentation

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CustomTestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CustomTestApplication)
            modules(TestKoinModules.getModules())
        }
    }
}

class CustomTestRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, CustomTestApplication::class.java.name, context)
    }
}