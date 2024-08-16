package com.safaricom.savings.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val primary_300 = Color(0xFF80b8f1)
val primary_400 = Color(0xFF338de9)
val primary_500 = Color(0xFF0071E3)

val secondary_400 = Color(0xFFfee433)
val secondary_500 = Color(0xFFfedd00)

val light_label_text_color = Color(0xFF828282)
val dark_label_text_color = Color(0xFF4f4f4f)

val light_surface = Color(0xFFFFFFFF)
val dark_surface = Color(0xFF000000)

val light_heavy_text = Color(0xFFF7F7F7)
val dark_heavy_text = Color(0xFF454545)

val light_background = Color(0xFFF5F5F7)
val dark_background = Color(0xFF272727)

val yellow = Color(0xFFfee433)
val purple = Color(0xFF6338EB)
val green = Color(0xFF3AB549)
val orange = Color(0xFFFB9502)
val pink = Color(0xFFF80F8A)
val blue = Color(0xFF1493FF)
val darkBlue = Color(0xFF034488)

val light_text_color = Color(0xFF111111)
val dark_text_color = Color(0xFFFFFFFF)
val light_subtext_color = Color(0xFF5D5D5D)
val dark_subtext_color = Color(0xFFE6E6E6)

@Composable
fun defaultTextColor() = if (!isSystemInDarkTheme()) light_text_color else dark_text_color

@Composable
fun textColorAlt() = if (!isSystemInDarkTheme()) light_subtext_color else dark_subtext_color

@Composable
fun dividerColor() = Color(if (!isSystemInDarkTheme()) 0xFFe6f1fc else 0xFFcce3f9)

@Composable
fun keyColor() = Color(if (!isSystemInDarkTheme()) 0xFF757575 else 0xFF828282)

@Composable
fun labelTextColor() = if (!isSystemInDarkTheme()) light_label_text_color else dark_label_text_color

@Composable
fun heavyTextColor() = if (!isSystemInDarkTheme())
    light_heavy_text
else
    dark_heavy_text
