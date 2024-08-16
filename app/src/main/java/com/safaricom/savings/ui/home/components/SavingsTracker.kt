package com.safaricom.savings.ui.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.safaricom.savings.R
import com.safaricom.savings.ui.components.PrimaryButtonView
import com.safaricom.savings.ui.components.RegularTextView
import com.safaricom.savings.ui.components.SmallSpacer
import com.safaricom.savings.ui.util.Radius
import com.safaricom.savings.ui.util.modOf
import com.safaricom.savings.viewModels.SavingsViewModel

@Composable
fun SavingsTrackerApp(modifier: Modifier, viewModel: SavingsViewModel) {
    var initialAmount by remember { mutableStateOf(50.0) }

    Column(
        modifier = modifier
    ) {
        SavingsList(modifier = modOf().weight(1f), viewModel = viewModel)
        OutlinedTextField(
            initialAmount.toString(),
            modifier = modOf().fillMaxWidth(),
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            shape = RoundedCornerShape(Radius.Card),
            label = { RegularTextView(stringResource(R.string.starting_amount_ksh)) },
            onValueChange = {
                initialAmount = it.toDoubleOrNull() ?: 50.0
            }
        )
        SmallSpacer()
        PrimaryButtonView(
            stringResource(R.string.update_savings),
            modifier = modOf().fillMaxWidth()
        ) {
            viewModel.updateSavings(initialAmount)
        }
    }
}
