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

    /**
     * Updates the savings records in the database with a new initial amount.
     *
     * This method first deletes all existing savings records. Then, it calculates the savings amount
     * for each week based on the initial amount and the week number. It creates a new `Savings` record
     * for each week and inserts it into the database. The amount for each week increases by Ksh. 50,
     * and the total is the cumulative savings up to that week.
     *
     * @param initialAmount The base amount to be saved in the first week. Each subsequent week's amount
     *                      increases by Ksh. 50.
     */
    override suspend fun updateSavings(initialAmount: Double) {
        // Delete all existing savings records
        savingsDao.deleteAll()

        // Initialize variables for savings calculation
        var cumulativeTotal = 0.0
        var currentAmount = initialAmount

        // Loop through each week of the year
        for (week in 1..52) {
            // Update the total saved up to the current week
            cumulativeTotal += currentAmount

            // Determine the date for the current week (e.g., the first day of the week)
            // For demonstration purposes, we're using a placeholder date format.
            // Replace this with actual logic to compute the date if needed.
            val date = "01/${week.toString().padStart(2, '0')}" // Placeholder logic

            // Create a new Savings record for the current week
            val savings = Savings(
                week = week,
                date = date,
                amount = currentAmount,
                total = cumulativeTotal, // Total is the cumulative amount saved up to this week
                saved = false
            )

            // Insert the Savings record into the database
            insertSavings(savings)

            // Increase the amount for the next week by Ksh. 50
            currentAmount += 50.0
        }
    }

    override fun getAllSavings(): List<Savings> {
        return savingsDao.getAllSavings()
    }

    override suspend fun deleteAllSavings() {
        savingsDao.deleteAll()
    }
}