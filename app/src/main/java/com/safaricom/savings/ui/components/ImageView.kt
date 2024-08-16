package com.safaricom.savings.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.safaricom.savings.R
import com.safaricom.savings.ui.util.IconSize
import com.safaricom.savings.ui.util.Padding
import com.safaricom.savings.ui.util.PrimaryColor
import com.safaricom.savings.ui.util.modOf

@Composable
fun ImageView(
    src: Any,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
    contentDescription: String? = stringResource(R.string.app_name),
    tint: Color? = null
) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(src)
            .crossfade(true)
            .build(),
        loading = {
            Center {
                CircularProgressIndicator(
                    color = PrimaryColor(),
                    modifier = Modifier
                        .height(48.dp)
                        .width(48.dp)
                        .padding(Padding.Small)
                )
            }
        },
        colorFilter = if (tint == null) null else ColorFilter.tint(tint),
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier
    )
}

@Composable
fun CircleImageView(
    src: Any,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
    contentDescription: String? = stringResource(R.string.app_name),
    tint: Color? = null
) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(src)
            .crossfade(true)
            .build(),
        loading = {
            Center {
                CircularProgressIndicator(
                    color = PrimaryColor(),
                    modifier = Modifier
                        .height(48.dp)
                        .width(48.dp)
                        .padding(Padding.Small)
                )
            }
        },
        colorFilter = if (tint == null) null else ColorFilter.tint(tint),
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier.clip(CircleShape)
    )
}

@Composable
fun PulsingImageView(
    mainImage: Int,
    mainSize: Dp = IconSize.Huge,
    topRightImage: Int? = R.drawable.ic_plus
) {
    Box {
        LottieAnim(
            R.raw.pulse,
            modOf()
                .size(mainSize * 1.5f)
                .align(Alignment.Center),
            tint = PrimaryColor(),
            loop = true
        )
        ImageView(
            mainImage,
            modifier = modOf()
                .size(mainSize)
                .align(Alignment.Center)
        )
        if (topRightImage != null)
            ImageView(
                topRightImage,
                modifier = modOf()
                    .padding(top = Padding.Large, end = Padding.Large)
                    .size(IconSize.Small)
                    .align(Alignment.TopEnd)
            )
    }
}