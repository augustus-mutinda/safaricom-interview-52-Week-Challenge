package com.safaricom.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.safaricom.data.dao.SavingsDao
import com.safaricom.data.repositories.SavingsRepositoryImpl
import com.safaricom.data.database.SavingsDatabase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest

class SavingsRepositoryImplTest : KoinTest {

    private lateinit var database: SavingsDatabase
    private lateinit var savingsDao: SavingsDao
    private lateinit var repository: SavingsRepositoryImpl

    @Before
    fun setup() {
        // Start Koin with a module for the test
        startKoin {
            modules(module {
                single {
                    Room.inMemoryDatabaseBuilder(
                        ApplicationProvider.getApplicationContext(), SavingsDatabase::class.java
                    ).build()
                }
                single { get<SavingsDatabase>().savingsDao() }
                single { SavingsRepositoryImpl(get()) }
            })
        }

        // Inject dependencies
        database = getKoin().get()
        savingsDao = getKoin().get()
        repository = getKoin().get()
    }

    @Test
    fun testUpdateSavings() = runBlocking {
        // Define the initial amount
        val initialAmount = 50.0

        // Call the updateSavings method
        repository.updateSavings(initialAmount)

        // Verify that records have been inserted correctly
        val savingsRecords = savingsDao.getAllSavings()
        assertEquals(52, savingsRecords.size) // Check that 52 records have been inserted

        // Check specific weeks' amounts and totals
        for (week in 1..52) {
            val expectedAmount = initialAmount + (week - 1) * 50
            val expectedTotal = initialAmount * week + 50.0 * (week * (week - 1)) / 2

            val record = savingsRecords.find { it.week == week }
            assertNotNull(record)
            assertEquals(expectedAmount, record?.amount ?: 0.0, 0.01)
            assertEquals(expectedTotal, record?.total ?: 0.0, 0.01)
        }
    }
}
