package com.safaricom.data.repositories

import com.safaricom.data.dao.SavingsDao
import com.safaricom.data.models.Savings

class SavingsRepositoryImpl(private val savingsDao: SavingsDao) : SavingsRepository {

    override suspend fun insertSavings(savings: Savings) {
        savingsDao.insert(savings)
    }

    override suspend fun paySaving(savings: Savings) {
        savingsDao.update(savings)
    }

    override suspend fun updateSavings(initialAmount: Double) {
        savingsDao.deleteAll()
        for (week in 1..52) {
            val amount = initialAmount * week
            insertSavings(Savings(week = week, amount = amount))
        }
    }

    override fun getAllSavings(): List<Savings> {
        return savingsDao.getAllSavings()
    }

    override suspend fun deleteAllSavings() {
        savingsDao.deleteAll()
    }
}