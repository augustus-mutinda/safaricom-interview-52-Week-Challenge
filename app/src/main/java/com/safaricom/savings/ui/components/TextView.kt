package com.safaricom.savings.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.safaricom.savings.theme.defaultTextColor
import com.safaricom.savings.theme.labelTextColor
import com.safaricom.savings.theme.outfitFamily
import com.safaricom.savings.theme.textColorAlt

@Composable
fun TextView(
    textAlign: TextAlign = TextAlign.Start,
    fontWeight: FontWeight,
    textColor: Color,
    underline: Boolean = false,
    textSize: TextUnit = 14.sp,
    text: String? = null,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text ?: "",
        modifier = modifier,
        color = textColor,
        fontSize = textSize,
        onTextLayout = onTextLayout,
        fontFamily = outfitFamily,
        textAlign = textAlign,
        fontWeight = fontWeight,
        textDecoration = if (underline)
            TextDecoration.Underline
        else
            null
    )
}

@Composable
fun SmallTextView(
    text: String? = null,
    underline: Boolean = false,
    textAlign: TextAlign = TextAlign.Start,
    textColor: Color = textColorAlt(),
    modifier: Modifier = Modifier,
) {
    TextView(
        text = text ?: "",
        modifier = modifier,
        textAlign = textAlign,
        underline = underline,
        textSize = 12.sp,
        textColor = textColor,
        fontWeight = FontWeight.Medium
    )
}

@Composable
fun LabelTextView(
    text: String? = null,
    underline: Boolean = false,
    textAlign: TextAlign = TextAlign.Start,
    textColor: Color? = labelTextColor(),
    modifier: Modifier = Modifier,
) {
    TextView(
        text = text ?: "",
        modifier = modifier,
        underline = underline,
        textAlign = textAlign,
        textSize = 14.sp,
        textColor = textColor ?: defaultTextColor(),
        fontWeight = FontWeight.Normal
    )
}

@Composable
fun RegularTextView(
    text: String? = null,
    underline: Boolean = false,
    textAlign: TextAlign = TextAlign.Start,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    textColor: Color? = null,
    modifier: Modifier = Modifier,
) {
    TextView(
        text = text ?: "",
        modifier = modifier,
        underline = underline,
        textAlign = textAlign,
        onTextLayout = onTextLayout,
        textColor = textColor ?: textColorAlt(),
        fontWeight = FontWeight.Normal
    )
}

@Composable
fun MediumTextView(
    text: String? = null,
    textAlign: TextAlign = TextAlign.Start,
    underline: Boolean = false,
    fontWeight: FontWeight = FontWeight.Medium,
    textColor: Color? = null,
    textSize: TextUnit? = null,
    modifier: Modifier = Modifier,
) {
    TextView(
        text = text ?: "",
        modifier = modifier,
        textAlign = textAlign,
        underline = underline,
        textColor = textColor ?: defaultTextColor(),
        textSize = textSize ?: 16.sp,
        fontWeight = fontWeight
    )
}

@Composable
fun SemiTextView(
    text: String? = null,
    underline: Boolean = false,
    textAlign: TextAlign = TextAlign.Start,
    textSize: TextUnit = 18.sp,
    textColor: Color? = null,
    modifier: Modifier = Modifier,
) {
    TextView(
        text = text ?: "",
        modifier = modifier,
        textAlign = textAlign,
        underline = underline,
        textColor = textColor ?: defaultTextColor(),
        textSize = textSize,
        fontWeight = FontWeight.SemiBold
    )
}

@Composable
fun SubTitleTextView(
    text: String? = null,
    underline: Boolean = false,
    textAlign: TextAlign = TextAlign.Start,
    textColor: Color? = null,
    modifier: Modifier = Modifier,
) {
    TextView(
        text = text ?: "",
        modifier = modifier,
        textAlign = textAlign,
        underline = underline,
        textColor = textColor ?: defaultTextColor(),
        textSize = 22.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun TitleTextView(
    text: String? = null,
    underline: Boolean = false,
    textAlign: TextAlign = TextAlign.Start,
    textColor: Color? = null,
    modifier: Modifier = Modifier,
) {
    TextView(
        text = text ?: "",
        modifier = modifier,
        textAlign = textAlign,
        underline = underline,
        textColor = textColor ?: defaultTextColor(),
        textSize = 28.sp,
        fontWeight = FontWeight.Bold
    )
}