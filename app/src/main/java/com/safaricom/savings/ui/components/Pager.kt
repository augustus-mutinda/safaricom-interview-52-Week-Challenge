package com.safaricom.savings.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.safaricom.savings.ui.util.Padding
import com.safaricom.savings.ui.util.PrimaryColor
import com.safaricom.savings.ui.util.modOf
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <T> Carousel(
    itemList: List<T>,
    autoScrollDuration: Long = 3000L,
    activeIndicatorColor: Color = MaterialTheme.colorScheme.primary,
    inActiveIndicatorColor: Color = MaterialTheme.colorScheme.surface,
    itemAltContent: (@Composable BoxScope.(Modifier, T) -> Unit)? = null,
    itemContent: (@Composable ColumnScope.(Modifier, T) -> Unit)? = null,
) {
    val pagerState: PagerState = rememberPagerState { itemList.size }

    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
    if (isDragged.not())
        with(pagerState) {
            var currentPageKey by remember { mutableIntStateOf(0) }
            LaunchedEffect(key1 = currentPageKey) {
                launch {
                    delay(timeMillis = autoScrollDuration)
                    val nextPage = try {
                        (currentPage + 1).mod(pageCount)
                    } catch (e: Exception) {
                        0
                    }
                    animateScrollToPage(page = nextPage)
                    currentPageKey = nextPage
                }
            }
        }

    if (itemAltContent != null)
        Box {
            HorizontalPager(
                modifier = modOf().align(Alignment.TopStart),
                state = pagerState
            ) { page ->
                itemAltContent(
                    Modifier.carouselTransition(page, pagerState),
                    itemList[page]
                )
            }

            DotIndicators(
                modifier = modOf()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = Padding.Tiny),
                pageCount = itemList.size,
                pagerState = pagerState,
                selectedColor = activeIndicatorColor,
                unselectedColor = inActiveIndicatorColor
            )
        }

    if (itemContent != null)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            HorizontalPager(state = pagerState) { page ->
                itemContent(Modifier.carouselTransition(page, pagerState), itemList[page])
            }

            DotIndicators(
                modifier = modOf(),
                pageCount = itemList.size,
                pagerState = pagerState,
                selectedColor = activeIndicatorColor,
                unselectedColor = inActiveIndicatorColor
            )
        }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DotIndicators(
    modifier: Modifier,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unselectedColor: Color = MaterialTheme.colorScheme.surface,
    pagerState: PagerState,
    pageCount: Int
) {
    Row(
        modifier = modifier
            .width(128.dp)
            .padding(Padding.Regular),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        repeat(pageCount) { iteration ->
            val color = if (pagerState.currentPage == iteration) selectedColor else unselectedColor
            Dot(color)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
fun Modifier.carouselTransition(page: Int, pagerState: PagerState) =
    graphicsLayer {
        val pageOffset =
            ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue

        val transformation =
            lerp(
                start = 0.7f,
                stop = 1f,
                fraction = 1f - pageOffset.coerceIn(0f, 1f)
            )
        alpha = transformation
        scaleY = transformation
    }

@Composable
fun Dot(color: Color = PrimaryColor()) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .size(8.dp)
            .background(color)
    )
}