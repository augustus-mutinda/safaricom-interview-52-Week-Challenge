package com.safaricom.savings.ui.components

import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun Modifier.shake(shouldShake: MutableState<Boolean>): Modifier = composed {
    val shakeDistance = 30.dp
    val shakeDuration = 300
    val shakeAnimatable = remember { Animatable(0f) }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    LaunchedEffect(shouldShake.value) {
        if (shouldShake.value) {
            val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            val vibrationEffect = VibrationEffect.createWaveform(
                longArrayOf(0, 50, 50), 0
            )
            coroutineScope.launch {
                shakeAnimatable.snapTo(0f) // Reset the animation

                vibrator.vibrate(vibrationEffect)

                shakeAnimatable.animateTo(
                    targetValue = shakeDistance.value,
                    animationSpec = infiniteRepeatable(
                        animation = tween(
                            durationMillis = 100,
                            easing = LinearEasing
                        ),
                        repeatMode = RepeatMode.Reverse
                    )
                )
            }
            delay(shakeDuration.toLong())
            vibrator.cancel()
            shakeAnimatable.stop()
            shouldShake.value = false
        }
    }

    this.then(
        Modifier.offset { IntOffset(shakeAnimatable.value.toInt(), 0) }
    )
}