package com.safaricom.savings.ui.util

import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

val toolbarHeight = 54.dp
val buttonHeight = 48.dp
val cardElevation = 4.dp
val border = 1.dp

object Radius {
    val None = 0.dp
    val Divider = 6.dp
    val Card = 12.dp
    val RoundButton = 24.dp
}

object Padding {
    val Zero = 0.dp
    val Tiny = 2.dp
    val Small = 4.dp
    val Regular = 8.dp
    val Medium = 16.dp
    val Large = 32.dp
    val Huge = 64.dp
}

object IconSize {
    val XTiny = 6.dp
    val Tiny = 12.dp
    val Small = 24.dp
    val Medium = 32.dp
    val Regular = 48.dp
    val Large = 64.dp
    val Huge = 96.dp
    val Banner = 200.dp
}

object Duration {
    val BottomSheet: Int = 500
}

object Elevation {
    val None = 0.dp

    @Composable
    fun card() = CardDefaults.cardElevation(defaultElevation = 4.dp)
}