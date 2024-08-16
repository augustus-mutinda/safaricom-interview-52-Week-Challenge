package com.safaricom.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.safaricom.data.dao.SavingsDao
import com.safaricom.data.models.Savings

/**
 * Room database class for managing the Savings app's database.
 * This class defines the database configuration and serves as the main access point for
 * the underlying SQLite database. It provides access to the [SavingsDao] for performing
 * database operations on the `Savings` entity.
 * @property savingsDao The DAO for accessing and manipulating savings data.
 */
@Database(entities = [Savings::class], version = 3, exportSchema = false)
abstract class SavingsDatabase : RoomDatabase() {

    /**
     * Provides access to the [SavingsDao] for performing database operations.
     * @return An instance of [SavingsDao] for accessing savings data.
     */
    abstract fun savingsDao(): SavingsDao
}