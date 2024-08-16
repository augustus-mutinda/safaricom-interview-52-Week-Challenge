package com.safaricom.savings

import android.app.Application
import com.safaricom.data.dataModule
import com.safaricom.savings.viewModels.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import timber.log.Timber

/**
 * Custom [Application] class for the Savings app.
 *
 * This class is the entry point for the application and is responsible for initializing
 * global configurations such as logging and dependency injection.
 */
class SavingsApp : Application() {

    /**
     * Called when the application is starting, before any other application objects have been created.
     * This is where you should initialize components that need to be set up when the app is launched.
     */
    override fun onCreate() {
        super.onCreate()
        startLogging()
        startKoin()
    }

    /**
     * Sets up Timber for logging throughout the app.
     *
     * This method plants a [Timber.DebugTree] which logs debug information. Timber is a logging library
     * that allows for flexible logging in Android applications.
     */
    private fun startLogging() {
        Timber.plant(Timber.DebugTree())
    }

    /**
     * Initializes Koin for dependency injection.
     *
     * This method sets up Koin with Android-specific configurations including the logger,
     * Android context, and the app's dependency modules.
     */
    private fun startKoin() {
        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@SavingsApp)
            modules(dataModule, viewModelModule)
        }
    }
}