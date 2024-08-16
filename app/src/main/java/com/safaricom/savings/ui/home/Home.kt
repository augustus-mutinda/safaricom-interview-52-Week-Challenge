package com.safaricom.savings.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.safaricom.data.sharedPref.PreferencesHelper
import com.safaricom.savings.ui.components.SemiTextView
import com.safaricom.savings.ui.home.components.SavingsTrackerApp
import com.safaricom.savings.ui.util.Padding
import com.safaricom.savings.ui.util.modOf
import com.safaricom.savings.viewModels.AuthViewModel
import com.safaricom.savings.viewModels.SavingsViewModel

@Composable
fun Home(
    authViewModel: AuthViewModel,
    savingsViewModel: SavingsViewModel,
    navController: NavHostController,
    preferencesHelper: PreferencesHelper
) {
    Column(
        modifier = modOf()
            .fillMaxWidth()
            .padding(Padding.Medium)
    ) {
        Row {
            SemiTextView("Good Morning")
        }
        SavingsTrackerApp(savingsViewModel)
    }
}