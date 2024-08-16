package com.safaricom.savings.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun <T> ListView(
    modifier: Modifier = Modifier,
    data: List<T>,
    build: @Composable (T) -> Unit
) {
    Column(modifier = modifier) {
        repeat(data.size) {
            build(data[it])
        }
    }
}