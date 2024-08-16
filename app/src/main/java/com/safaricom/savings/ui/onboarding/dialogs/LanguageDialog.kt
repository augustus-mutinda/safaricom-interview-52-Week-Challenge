package com.safaricom.savings.ui.onboarding.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.safaricom.savings.R
import com.safaricom.savings.ui.components.ComposableBottomSheetDialog
import com.safaricom.savings.ui.components.ImageView
import com.safaricom.savings.ui.components.MediumSpacer
import com.safaricom.savings.ui.components.MediumTextView
import com.safaricom.savings.ui.components.RegularTextView
import com.safaricom.savings.ui.components.SmallSpacer
import com.safaricom.savings.ui.components.shake
import com.safaricom.savings.ui.util.IconSize
import com.safaricom.savings.ui.util.OnBackgroundColor
import com.safaricom.savings.ui.util.Padding
import com.safaricom.savings.ui.util.PrimaryColor
import com.safaricom.savings.ui.util.SurfaceColor
import com.safaricom.savings.ui.util.modOf
import com.safaricom.data.utils.Languages

@Composable
fun LanguageDialog(active: String, onDismiss: (String?) -> Unit) {
    ComposableBottomSheetDialog(
        onDismissRequest = { onDismiss.invoke(null) }
    ) {
        Column(
            modifier = modOf()
                .fillMaxWidth()
                .padding(horizontal = Padding.Medium)
        ) {
            RegularTextView(stringResource(R.string.please_choose_your_language))
            MediumSpacer()
            LanguageRow(active, Languages.EN) { onDismiss.invoke(Languages.EN) }
            LanguageRow(active, Languages.SW) { onDismiss.invoke(Languages.SW) }
        }
    }
}

@Composable
fun LanguageRow(active: String, language: String, onSelect: () -> Unit) {
    val isActive = active == language
    val shake = remember { mutableStateOf(false) }

    Row(modifier = modOf()
        .shake(shake)
        .padding(Padding.Medium)
        .clickable {
            if (isActive) shake.value = true
            else onSelect.invoke()
        }
    ) {
        MediumTextView(
            stringResource(if (language == Languages.EN) R.string.english else R.string.swahili),
            textColor = if (isActive) PrimaryColor() else OnBackgroundColor(),
            modifier = modOf().weight(1f)
        )
        SmallSpacer()
        ImageView(
            R.drawable.chevron_right,
            modifier = modOf()
                .size(IconSize.Small)
                .background(SurfaceColor(), CircleShape)
                .padding(Padding.Tiny),
            tint = OnBackgroundColor()
        )
        SmallSpacer()
    }
}