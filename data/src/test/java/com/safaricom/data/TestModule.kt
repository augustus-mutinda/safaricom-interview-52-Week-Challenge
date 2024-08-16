package com.safaricom.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.safaricom.data.database.SavingsDatabase
import com.safaricom.data.repositories.SavingsRepository
import com.safaricom.data.repositories.SavingsRepositoryImpl
import com.safaricom.data.sharedPref.PreferencesHelper
import org.koin.dsl.module
import org.mockito.Mockito.mock

val testModule = module {
    single {
        Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            SavingsDatabase::class.java
        ).allowMainThreadQueries().build()
    }
    single { get<SavingsDatabase>().savingsDao() }
    single<SavingsRepository> { SavingsRepositoryImpl(get()) }
    single<PreferencesHelper> { mock(PreferencesHelper::class.java) }
}