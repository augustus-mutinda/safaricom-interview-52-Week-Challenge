package com.safaricom.savings.ui.onboarding.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.safaricom.savings.R
import com.safaricom.savings.ui.components.ImageView
import com.safaricom.savings.ui.components.MediumSpacer
import com.safaricom.savings.ui.components.MediumTextView
import com.safaricom.savings.ui.components.SmallSpacer
import com.safaricom.savings.ui.util.IconSize
import com.safaricom.savings.ui.util.OnBackgroundColor
import com.safaricom.savings.ui.util.Padding
import com.safaricom.savings.ui.util.modOf

@Composable
fun SettingsItem(
    icon: Int,
    name: String,
    value: String? = null,
    showSuffix: Boolean = true,
    onAction: () -> Unit
) {
    Column(modifier = modOf()) {
        MediumSpacer()
        Row(
            modifier = modOf()
                .fillMaxWidth()
                .padding(Padding.Regular)
                .clickable { onAction.invoke() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImageView(
                icon,
                modifier = modOf().size(IconSize.Small),
                tint = OnBackgroundColor(),
            )
            MediumSpacer()
            MediumTextView(name, modifier = modOf().weight(1f))
            SmallSpacer()
            MediumTextView(value)
            if (showSuffix)
                ImageView(
                    R.drawable.chevron_right,
                    tint = OnBackgroundColor(),
                    modifier = modOf().size(IconSize.Small)
                )
        }
        MediumSpacer()
        HorizontalDivider(
            modifier = modOf()
                .fillMaxWidth()
                .padding(start = Padding.Large)
        )
    }
}