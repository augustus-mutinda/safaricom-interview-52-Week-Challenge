package com.safaricom.savings.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.safaricom.savings.R
import com.safaricom.savings.ui.util.IconSize
import com.safaricom.savings.ui.util.Padding
import com.safaricom.savings.ui.util.modOf
import com.safaricom.savings.ui.util.toolbarHeight

@Composable
fun Toolbar(
    navController: NavController,
    title: String? = null,
    alignCenter: Boolean = false,
    transparent: Boolean = false,
    textColor: Color = MaterialTheme.colorScheme.onBackground,
    barColor: Color = MaterialTheme.colorScheme.background,
    suffix: (@Composable () -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(toolbarHeight)
            .background(if (transparent) Color.Transparent else barColor),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SmallSpacer()
        ImageView(
            R.drawable.ic_arrow_left,
            modifier = Modifier
                .padding(Padding.Regular)
                .size(IconSize.Small)
                .clickable { navController.popBackStack() },
            tint = textColor ?: MaterialTheme.colorScheme.onBackground,
        )
        if (alignCenter) Spacer(modifier = Modifier.weight(1f))
        else SmallSpacer()
        if (title != null)
            SemiTextView(
                text = title.replace("\n", ""),
                textAlign = TextAlign.Center,
                textColor = textColor,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(0.dp)
            )
        Spacer(modifier = Modifier.weight(1f))
        if (suffix != null) suffix()
        if (alignCenter && suffix == null) Spacer(modifier = modOf().width(IconSize.Small))
        Spacer(modifier = Modifier.width(Padding.Medium))
    }
}