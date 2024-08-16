package com.safaricom.savings.viewModels

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

/**
 * Koin module for providing ViewModel instances.
 * This module defines the ViewModels used in the application and provides them for dependency injection
 * using Koin. Each ViewModel is bound to its respective instance factory.
 */
internal val viewModelModule = module {
    viewModelOf(::SavingsViewModel)
    viewModelOf(::AuthViewModel)
}