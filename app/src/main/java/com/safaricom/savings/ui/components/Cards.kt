package com.safaricom.savings.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.safaricom.savings.ui.util.Padding
import com.safaricom.savings.ui.util.Radius
import com.safaricom.savings.ui.util.cardElevation

@Composable
fun SolidCard(
    modifier: Modifier = Modifier,
    containerColor: Color? = null,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier
            .padding(vertical = Padding.Regular),
        shape = RoundedCornerShape(Radius.Card),
        colors = CardDefaults.cardColors(
            containerColor = containerColor ?: if (isSystemInDarkTheme())
                MaterialTheme.colorScheme.surface
            else
                MaterialTheme.colorScheme.background,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = cardElevation)
    ) {
        content()
    }
}

@Composable
fun FlatCard(
    modifier: Modifier = Modifier,
    containerColor: Color? = null,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier
            .padding(vertical = Padding.Regular),
        shape = RoundedCornerShape(Radius.Card),
        colors = CardDefaults.cardColors(
            containerColor = containerColor ?: MaterialTheme.colorScheme.surface,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        content()
    }
}