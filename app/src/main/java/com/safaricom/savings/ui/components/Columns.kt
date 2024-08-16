package com.safaricom.savings.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.safaricom.savings.ui.util.Padding

@Composable
fun Center(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        content()
    }
}

@Composable
fun <T> VerticalGrid(
    items: List<T>,
    height: Int,
    modifier: Modifier = Modifier,
    columns: Int = 2,
    contentPadding: PaddingValues = PaddingValues(Padding.Zero),
    content: @Composable (T) -> Unit
) {
    val heightFactor = (items.size / columns) + 1

    LazyVerticalGrid(
        modifier = modifier.height((height * heightFactor).dp),
        columns = GridCells.Fixed(columns),
        contentPadding = contentPadding
    ) {
        items(items.size) { position ->
            content(items[position])
        }
    }
}