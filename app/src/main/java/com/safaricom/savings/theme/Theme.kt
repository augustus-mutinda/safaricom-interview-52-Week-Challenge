package com.safaricom.savings.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.safaricom.data.sharedPref.PreferencesHelper

private val darkColorScheme = darkColorScheme(
    primary = primary_500,
    onPrimary = Color.White,
    secondary = secondary_500,
    onSecondary = Color.White,
    background = dark_surface,
    onBackground = light_surface,
    surface = dark_background,
    onSurface = light_background
)

private val lightColorScheme = lightColorScheme(
    primary = primary_400,
    onPrimary = Color.White,
    secondary = secondary_400,
    onSecondary = Color.White,
    background = light_surface,
    onBackground = dark_surface,
    surface = light_background,
    onSurface = dark_background,
)

@Composable
fun ChamaHubTheme(
    preferencesHelper: PreferencesHelper,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) darkColorScheme else lightColorScheme,
        typography = Typography
    ) {
        content()
    }
}