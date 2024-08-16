package com.safaricom.savings.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import com.safaricom.savings.ui.components.SemiTextView
import com.safaricom.savings.ui.home.components.SavingsTrackerApp
import com.safaricom.savings.ui.util.Padding
import com.safaricom.savings.ui.util.modOf
import com.safaricom.savings.viewModels.SavingsViewModel

@Composable
fun Home(
    savingsViewModel: SavingsViewModel
) {
    Column(
        modifier = modOf()
            .fillMaxSize()
            .padding(Padding.Medium)
    ) {
        Row {
            SemiTextView("Good Morning")
        }
        SavingsTrackerApp(modOf().weight(1f), savingsViewModel)
    }
}