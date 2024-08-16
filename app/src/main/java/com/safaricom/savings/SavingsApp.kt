package com.safaricom.savings

import android.app.Application
import com.safaricom.data.dataModule
import com.safaricom.savings.viewModels.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import timber.log.Timber

class SavingsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startLogging()
        startKoin()
    }

    private fun startLogging() {
        Timber.plant(Timber.DebugTree())
    }

    private fun startKoin() {
        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@SavingsApp)
            modules(dataModule, viewModelModule)
        }
    }
}