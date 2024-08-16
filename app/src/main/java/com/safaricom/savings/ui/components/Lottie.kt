package com.safaricom.savings.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.rememberLottieDynamicProperties
import com.airbnb.lottie.compose.rememberLottieDynamicProperty

@Composable
fun LottieAnim(
    src: Int,
    modifier: Modifier = Modifier,
    loop: Boolean = false,
    tint: Color? = null
) {
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(src)
    )

    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
        restartOnPlay = loop
    )
    val preloaderProgressForever by animateLottieCompositionAsState(
        preloaderLottieComposition,
        restartOnPlay = loop,
        iterations = LottieConstants.IterateForever,
    )

    if (tint != null) {
        val dynamicProperties = rememberLottieDynamicProperties(
            rememberLottieDynamicProperty(
                property = LottieProperty.COLOR_FILTER,
                value = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                    tint.hashCode(),
                    BlendModeCompat.SRC_ATOP
                ),
                keyPath = arrayOf(
                    "**"
                )
            )
        )
        LottieAnimation(
            composition = preloaderLottieComposition,
            progress = if (loop) preloaderProgressForever else preloaderProgress,
            modifier = modifier,
            dynamicProperties = dynamicProperties
        )
    } else
        LottieAnimation(
            composition = preloaderLottieComposition,
            progress = if (loop) preloaderProgressForever else preloaderProgress,
            modifier = modifier
        )
}