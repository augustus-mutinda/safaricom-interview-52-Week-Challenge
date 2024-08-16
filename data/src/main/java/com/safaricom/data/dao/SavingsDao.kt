package com.safaricom.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.safaricom.data.models.Savings

@Dao
interface SavingsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(savings: Savings)

    @Query("SELECT * FROM savings ORDER BY week ASC")
    fun getAllSavings(): List<Savings>

    @Update
    suspend fun update(savings: Savings)

    @Query("DELETE FROM savings")
    suspend fun deleteAll()
}