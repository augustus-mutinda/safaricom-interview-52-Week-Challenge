package com.safaricom.savings.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.safaricom.savings.theme.green
import com.safaricom.savings.ui.components.Center
import com.safaricom.savings.ui.components.FlatCard
import com.safaricom.savings.ui.components.MediumTextView
import com.safaricom.savings.ui.components.RegularTextView
import com.safaricom.savings.ui.util.Padding
import com.safaricom.savings.ui.util.PrimaryColor
import com.safaricom.savings.ui.util.Radius
import com.safaricom.savings.ui.util.modOf
import com.safaricom.savings.viewModels.SavingsViewModel

@Composable
fun SavingsList(modifier: Modifier, viewModel: SavingsViewModel) {
    val savings by viewModel.allSavings.observeAsState()
    val savingsList = savings.orEmpty()

    LazyColumn(modifier = modifier) {
        items(savingsList.size) { index ->
            val saving = savingsList[index]
            FlatCard(
                modifier = modOf()
                    .fillMaxWidth()
            ) {
                Row(
                    modOf().padding(Padding.Medium),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modOf().weight(1f)) {
                        RegularTextView("Week ${saving.week}")
                        MediumTextView(saving.date)
                    }
                    Column(modOf().weight(1f)) {
                        RegularTextView("Amount")
                        MediumTextView("Ksh. ${saving.amount}")
                    }
                    Column(modOf().weight(1f)) {
                        RegularTextView("Total")
                        MediumTextView("Ksh. ${saving.total}")
                    }
                    Center(
                        modifier = modOf()
                            .background(
                                (if (saving.saved) green else PrimaryColor()).copy(alpha = 0.2f),
                                RoundedCornerShape(Radius.RoundButton)
                            )
                            .padding(
                                start = Padding.Medium,
                                end = Padding.Medium,
                                top = Padding.Tiny,
                                bottom = Padding.Tiny
                            )
                            .clickable {
                                if (!saving.saved) {
                                    saving.saved = true
                                    viewModel.paySaving(saving)
                                }
                            }
                    ) {
                        MediumTextView(
                            if (saving.saved) "Saved" else "Save",
                            modifier = modOf(),
                            textColor = if (saving.saved) green else PrimaryColor()
                        )
                    }
                }
            }
        }
    }
}