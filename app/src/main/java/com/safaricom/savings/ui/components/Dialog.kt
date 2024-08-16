package com.safaricom.savings.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.safaricom.savings.R
import com.safaricom.savings.theme.dividerColor
import com.safaricom.savings.ui.util.Duration
import com.safaricom.savings.ui.util.IconSize
import com.safaricom.savings.ui.util.Padding
import com.safaricom.savings.ui.util.Radius
import com.safaricom.savings.ui.util.modOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ComposableDialog(
    onDismissRequest: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val animateTrigger = remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        launch {
            animateTrigger.value = true
        }
    }

    Dialog(onDismissRequest = { onDismissRequest?.invoke() }) {
        AnimatedScaleInTransition(visible = animateTrigger.value) {
            content()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposableBottomSheetDialog(
    title: String? = null,
    onDismissRequest: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    val animateTrigger = remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        launch {
            animateTrigger.value = true
        }
    }
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    ModalBottomSheet(
        containerColor = MaterialTheme.colorScheme.background,
        sheetState = modalBottomSheetState,
        dragHandle = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .width(60.dp)
                        .padding(top = Padding.Medium)
                        .height(Radius.Divider)
                        .align(TopCenter)
                        .clip(RoundedCornerShape(Radius.Divider))
                        .background(dividerColor())
                )
                if (title != null)
                    SemiTextView(
                        title,
                        modifier = modOf()
                            .align(Alignment.TopStart)
                            .padding(Padding.Medium)
                    )
                ImageView(
                    src = R.drawable.close_outline,
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .size(IconSize.Regular)
                        .align(TopEnd)
                        .padding(Padding.Zero, Padding.Zero, Padding.Medium, Padding.Zero)
                        .clickable {
                            onDismissRequest?.invoke()
                        }
                )
            }
        },
        onDismissRequest = {
            coroutineScope.launch {
                startDismissWithExitAnimation(animateTrigger, onDismissRequest)
            }
        }) {
        AnimatedSlideInTransition(
            visible = animateTrigger.value
        ) {
            content()
        }
    }
}

@Composable
fun AnimatedSlideInTransition(
    modifier: Modifier = Modifier,
    visible: Boolean,
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = visible,
        enter = slideInVertically(
            initialOffsetY = { it }
        ),
        exit = slideOutVertically(
            targetOffsetY = { it }
        ),
        content = content
    )
}

@Composable
fun AnimatedScaleInTransition(
    visible: Boolean,
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = scaleIn(
            animationSpec = tween(Duration.BottomSheet)
        ),
        exit = scaleOut(
            animationSpec = tween(Duration.BottomSheet)
        ),
        content = content
    )
}

suspend fun startDismissWithExitAnimation(
    animateTrigger: MutableState<Boolean>,
    onDismissRequest: (() -> Unit)? = null
) {
    animateTrigger.value = false
    delay(Duration.BottomSheet.toLong())
    if (onDismissRequest != null) onDismissRequest()
}
