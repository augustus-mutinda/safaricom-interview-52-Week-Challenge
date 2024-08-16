package com.safaricom.savings.viewModels

import androidx.lifecycle.MutableLiveData
import com.safaricom.data.models.Savings
import com.safaricom.data.repositories.SavingsRepository
import com.safaricom.data.sharedPref.PreferencesHelper
import kotlinx.coroutines.launch

class SavingsViewModel(
    var preferencesHelper: PreferencesHelper,
    var savingsRepository: SavingsRepository
) : BaseViewModel() {
    val allSavings = MutableLiveData(listOf<Savings>())

    init {
        getAllSavings()
    }

    fun getAllSavings() {
        bgScope.launch {
            allSavings.postValue(savingsRepository.getAllSavings())
        }
    }

    fun insertSavings(week: Int, amount: Double) {
        bgScope.launch {
            savingsRepository.insertSavings(Savings(week = week, amount = amount))
        }
    }

    fun updateSavings(initialAmount: Double) {
        bgScope.launch {
            savingsRepository.updateSavings(initialAmount)
            getAllSavings()
        }
    }
}