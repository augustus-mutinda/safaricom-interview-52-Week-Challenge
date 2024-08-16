package com.safaricom.savings.viewModels

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

internal val viewModelModule = module {
    viewModelOf(::SavingsViewModel)
    viewModelOf(::AuthViewModel)
}