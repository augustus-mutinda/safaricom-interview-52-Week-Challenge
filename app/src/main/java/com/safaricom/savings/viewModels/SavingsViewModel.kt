package com.safaricom.savings.viewModels

import androidx.lifecycle.MutableLiveData
import com.safaricom.data.models.Savings
import com.safaricom.data.repositories.SavingsRepository
import kotlinx.coroutines.launch

class SavingsViewModel(
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

    fun paySaving(saving: Savings) {
        bgScope.launch {
            savingsRepository.paySaving(saving)
            getAllSavings()
        }
    }

    fun updateSavings(initialAmount: Double) {
        bgScope.launch {
            savingsRepository.updateSavings(initialAmount)
            getAllSavings()
        }
    }
}