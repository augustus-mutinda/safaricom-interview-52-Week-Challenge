package com.safaricom.savings.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.safaricom.savings.R

val outfitFamily = FontFamily(
    Font(R.font.urbanist_extra_light, FontWeight.Thin),
    Font(R.font.urbanist_light, FontWeight.ExtraLight),
    Font(R.font.urbanist_regular, FontWeight.Light),
    Font(R.font.urbanist_medium, FontWeight.Normal),
    Font(R.font.urbanist_semi_bold, FontWeight.Medium),
    Font(R.font.urbanist_bold, FontWeight.Bold),
    Font(R.font.urbanist_extra_bold, FontWeight.SemiBold),
    Font(R.font.urbanist_extra_bold, FontWeight.ExtraBold),
    Font(R.font.urbanist_black, FontWeight.Black)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)