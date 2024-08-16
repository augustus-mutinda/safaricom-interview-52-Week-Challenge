package com.safaricom.savings.ui.util

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.CoroutineScope

fun <T> MutableState<T>.post(value: T) {
    this.value = value
}

@Composable
fun modOf() = Modifier

@Composable
fun LaunchedUnitEffect(block: suspend CoroutineScope.() -> Unit) {
    LaunchedEffect(Unit, block = block)
}

@Composable
fun PrimaryColor() = MaterialTheme.colorScheme.primary

@Composable
fun OnPrimaryColor() = Color.White

@Composable
fun SecondaryColor() = MaterialTheme.colorScheme.secondary

@Composable
fun BackgroundColor() = MaterialTheme.colorScheme.background

@Composable
fun OnBackgroundColor() = MaterialTheme.colorScheme.onBackground

@Composable
fun SurfaceColor() = MaterialTheme.colorScheme.surface

@Composable
fun OnSurfaceColor() = MaterialTheme.colorScheme.onSurface