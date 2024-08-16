package com.safaricom.savings.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.safaricom.savings.theme.dividerColor
import com.safaricom.savings.theme.labelTextColor
import com.safaricom.savings.ui.util.BackgroundColor
import com.safaricom.savings.ui.util.IconSize
import com.safaricom.savings.ui.util.OnBackgroundColor
import com.safaricom.savings.ui.util.Padding
import com.safaricom.savings.ui.util.PrimaryColor
import com.safaricom.savings.ui.util.Radius
import com.safaricom.savings.ui.util.SecondaryColor
import com.safaricom.savings.ui.util.buttonHeight
import com.safaricom.savings.ui.util.modOf

@Composable
fun ButtonView(
    modifier: Modifier = Modifier,
    text: String? = null,
    loadingMessage: String? = null,
    textColor: Color = Color.White,
    borderWidth: Dp = 0.dp,
    radius: Dp = Radius.Card,
    background: Color = MaterialTheme.colorScheme.primary,
    borderColor: Color = Color.Transparent,
    enabled: Boolean = true,
    isOutline: Boolean = false,
    isLoading: Boolean = false,
    prefix: (@Composable () -> Unit)? = null,
    suffix: (@Composable () -> Unit)? = null,
    onClick: (() -> Unit)? = null
) {
    Button(
        modifier = modifier.height(buttonHeight),
        border = BorderStroke(borderWidth, if (isOutline) background else borderColor),
        enabled = enabled || isLoading,
        shape = RoundedCornerShape(radius),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isOutline) Color.Transparent else background,
            contentColor = if (isOutline) background else textColor
        ),
        onClick = { onClick?.invoke() }
    ) {
        if (isLoading)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (!loadingMessage.isNullOrEmpty())
                    MediumTextView(
                        loadingMessage,
                        fontWeight = FontWeight.SemiBold,
                        textColor = if (!enabled) labelTextColor() else if (isOutline) background else textColor
                    )
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(24.dp)
                        .padding(4.dp),
                    strokeWidth = 2.dp,
                    color = if (!enabled) labelTextColor() else if (isOutline) background else textColor
                )
            }
        else {
            prefix?.let {
                it.invoke()
                RegularSpacer()
            }
            MediumTextView(
                text = text,
                textColor = if (!enabled) labelTextColor() else if (isOutline) background else textColor
            )
            suffix?.let {
                RegularSpacer()
                it.invoke()
            }
        }
    }
}

@Composable
fun PrimaryButtonView(
    text: String? = null,
    loadingMessage: String? = null,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    textColor: Color? = null,
    radius: Dp = Radius.Card,
    modifier: Modifier = Modifier,
    prefix: (@Composable () -> Unit)? = null,
    suffix: (@Composable () -> Unit)? = null,
    onClick: (() -> Unit)? = null,
) {
    ButtonView(
        text = text ?: "",
        modifier = modifier,
        enabled = enabled,
        background = PrimaryColor(),
        textColor = if (enabled) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground,
        loadingMessage = loadingMessage,
        isLoading = isLoading,
        radius = radius,
        prefix = prefix,
        suffix = suffix,
        onClick = onClick
    )
}

@Composable
fun ButtonView(
    modifier: Modifier = Modifier,
    text: String? = null,
    color: Color = MaterialTheme.colorScheme.primary,
    textColor: Color = Color.Black,
    radius: Dp = Radius.Card,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    prefix: (@Composable () -> Unit)? = null,
    suffix: (@Composable () -> Unit)? = null,
    onClick: () -> Unit
) {
    ButtonView(
        text = text ?: "",
        modifier = modifier,
        enabled = enabled,
        background = color,
        textColor = textColor,
        radius = radius,
        loadingMessage = null,
        prefix = prefix,
        suffix = suffix,
        isLoading = isLoading,
        onClick = onClick
    )
}

@Composable
fun OutlineButtonView(
    text: String? = null,
    color: Color = PrimaryColor(),
    textColor: Color = SecondaryColor(),
    radius: Dp = Radius.Card,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    loadingMessage: String = "",
    modifier: Modifier = Modifier,
    prefix: (@Composable () -> Unit)? = null,
    suffix: (@Composable () -> Unit)? = null,
    onClick: (() -> Unit)? = null
) {
    ButtonView(
        text = text ?: "",
        modifier = modifier,
        enabled = enabled,
        background = color,
        textColor = textColor,
        radius = radius,
        borderWidth = 1.dp,
        loadingMessage = loadingMessage,
        isOutline = true,
        prefix = prefix,
        suffix = suffix,
        isLoading = isLoading,
        onClick = onClick
    )
}

@Composable
fun BackgroundButton(
    text: String,
    prefix: Int? = null,
    enabled: Boolean = true,
    modifier: Modifier = modOf(),
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(
                if (enabled) OnBackgroundColor() else dividerColor(),
                RoundedCornerShape(Radius.Card)
            )
            .clickable { if (enabled) onClick.invoke() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        if (prefix != null)
            ImageView(
                prefix,
                tint = if (enabled) BackgroundColor() else Color.White,
                modifier = modOf()
                    .padding(Padding.Tiny)
                    .size(IconSize.Small)
            )
        SmallSpacer()
        MediumTextView(text, textColor = if (enabled) BackgroundColor() else Color.White)
    }
}