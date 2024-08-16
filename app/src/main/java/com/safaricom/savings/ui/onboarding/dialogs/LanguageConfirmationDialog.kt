package com.safaricom.savings.ui.onboarding.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.safaricom.savings.R
import com.safaricom.savings.ui.components.ComposableBottomSheetDialog
import com.safaricom.savings.ui.components.MediumSpacer
import com.safaricom.savings.ui.components.OutlineButtonView
import com.safaricom.savings.ui.components.PrimaryButtonView
import com.safaricom.savings.ui.components.RegularTextView
import com.safaricom.savings.ui.components.SemiTextView
import com.safaricom.savings.ui.components.SmallSpacer
import com.safaricom.savings.ui.util.Padding
import com.safaricom.savings.ui.util.modOf

@Composable
fun LanguageConfirmationDialog(restart: (Boolean?) -> Unit) {
    ComposableBottomSheetDialog(
        onDismissRequest = { restart.invoke(null) }) {
        Column(
            modifier = modOf()
                .fillMaxWidth()
                .padding(Padding.Medium)
        ) {
            SemiTextView(stringResource(R.string.restart_required))
            SmallSpacer()
            RegularTextView(stringResource(R.string.restart_required_message))
            MediumSpacer()
            Row(modOf().fillMaxWidth()) {
                SmallSpacer()
                OutlineButtonView(
                    stringResource(R.string.cancel),
                    modifier = modOf().weight(1f)
                ) { restart.invoke(false) }
                SmallSpacer()
                PrimaryButtonView(
                    stringResource(R.string.restart),
                    modifier = modOf().weight(1f)
                ) { restart.invoke(true) }
                SmallSpacer()
            }
            SmallSpacer()
        }
    }
}