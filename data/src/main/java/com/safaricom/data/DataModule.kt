package com.safaricom.data

import androidx.room.Room
import com.safaricom.data.repositories.SavingsRepository
import com.safaricom.data.repositories.SavingsRepositoryImpl
import com.safaricom.data.services.SavingsDatabase
import com.safaricom.data.sharedPref.PreferencesHelper
import com.safaricom.data.sharedPref.PreferencesHelperImpl
import com.safaricom.data.utils.Constants
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            SavingsDatabase::class.java,
            Constants.ROOMDB
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<SavingsDatabase>().savingsDao() }

    single<SavingsRepository> { SavingsRepositoryImpl(get()) }

    single<PreferencesHelper> { PreferencesHelperImpl(androidContext(), Constants.PREFERENCES) }
}