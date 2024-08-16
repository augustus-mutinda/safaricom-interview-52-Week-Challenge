package com.safaricom.savings.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomSpacer(height: Dp = 8.dp) {
    Box(modifier = Modifier.size(height))
}

@Composable
fun SmallSpacer(height: Dp = 4.dp) {
    Box(modifier = Modifier.size(height))
}
@Composable
fun RegularSpacer(height: Dp = 8.dp) {
    Box(modifier = Modifier.size(height))
}

@Composable
fun MediumSpacer(height: Dp = 16.dp) {
    Box(modifier = Modifier.size(height))
}

@Composable
fun LargeSpacer(height: Dp = 32.dp) {
    Box(modifier = Modifier.size(height))
}