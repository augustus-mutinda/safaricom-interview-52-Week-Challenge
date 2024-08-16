package com.safaricom.data.repositories

import com.safaricom.data.dao.SavingsDao
import com.safaricom.data.models.Savings
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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

        // Initialize date variables
        val startDate = LocalDate.of(2024, 1, 1) // Adjust the year as needed
        val formatter = DateTimeFormatter.ofPattern("dd/MM")

        // Loop through each week of the year
        for (week in 1..52) {
            // Update the total saved up to the current week
            cumulativeTotal += currentAmount

            // Calculate the date for the current week
            val date = startDate.plusWeeks(week - 1L).with(java.time.DayOfWeek.MONDAY)
            val formattedDate = date.format(formatter)

            // Create a new Savings record for the current week
            val savings = Savings(
                week = week,
                date = formattedDate,
                amount = currentAmount,
                total = cumulativeTotal, // Total is the cumulative amount saved up to this week
                saved = false
            )

            // Insert the Savings record into the database
            insertSavings(savings)

            // Increase the amount for the next week by Ksh. 50
            currentAmount += initialAmount
        }
    }


    override fun getAllSavings(): List<Savings> {
        return savingsDao.getAllSavings()
    }

    override suspend fun deleteAllSavings() {
        savingsDao.deleteAll()
    }
}