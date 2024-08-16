package com.safaricom.data.repositories

import com.safaricom.data.models.Savings

interface SavingsRepository {
    suspend fun insertSavings(savings: Savings)
    suspend fun paySaving(savings: Savings)
    suspend fun updateSavings(initialAmount: Double)
    fun getAllSavings(): List<Savings>
    suspend fun deleteAllSavings()
}