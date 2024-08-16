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

/**
 * Koin module for providing data-related dependencies.
 *
 * This module defines how to provide instances of the database, DAOs, repositories, and preferences
 * using Koin. It sets up the Room database and provides single instances of various components
 * for dependency injection.
 */
val dataModule = module {

    /**
     * Provides a singleton instance of [SavingsDatabase] using Room's databaseBuilder.
     *
     * Configures the Room database with a fallback strategy for destructive migration,
     * which recreates the database if there are schema changes.
     *
     * @return An instance of [SavingsDatabase].
     */
    single {
        Room.databaseBuilder(
            androidContext(),                // The Android context used to access the database
            SavingsDatabase::class.java,     // The database class
            Constants.ROOMDB                 // The name of the database file
        )
            .fallbackToDestructiveMigration() // Recreates the database on schema changes
            .build()
    }

    /**
     * Provides a singleton instance of [SavingsDao] from the [SavingsDatabase] instance.
     *
     * This DAO is used for performing database operations on the Savings table.
     *
     * @return An instance of [SavingsDao].
     */
    single { get<SavingsDatabase>().savingsDao() }

    /**
     * Provides a singleton instance of [SavingsRepository].
     *
     * This repository implementation handles data operations and provides a clean API
     * for accessing savings data.
     *
     * @return An instance of [SavingsRepository].
     */
    single<SavingsRepository> { SavingsRepositoryImpl(get()) }

    /**
     * Provides a singleton instance of [PreferencesHelper].
     *
     * This implementation manages user preferences using shared preferences.
     *
     * @return An instance of [PreferencesHelper].
     */
    single<PreferencesHelper> { PreferencesHelperImpl(androidContext(), Constants.PREFERENCES) }
}