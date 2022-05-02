package com.jonecx.qwit

import android.app.Application
import com.jonecx.qwit.di.injectedModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber
import timber.log.Timber.DebugTree

class Qwit : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@Qwit)
            modules(injectedModules)
        }
        setupLogging()
    }

    private fun setupLogging() {
        if (BuildConfig.DEBUG)
            Timber.plant(DebugTree())
    }
}
