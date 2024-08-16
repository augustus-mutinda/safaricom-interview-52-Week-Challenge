package com.safaricom.savings.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import com.safaricom.data.utils.Event
import com.safaricom.data.utils.ResourceState
import com.safaricom.savings.R
import com.safaricom.savings.ui.util.IconSize
import com.safaricom.savings.ui.util.modOf

@Composable
fun LoadingWidget(loadingState: LiveData<Event<@ResourceState Int?>>) {
    val loading by loadingState.observeAsState()
    if (loading?.peekContent() == ResourceState.STATE_LOADING)
        ComposableDialog {
            LottieAnim(R.raw.loader, modOf().size(IconSize.Large))
        }
}