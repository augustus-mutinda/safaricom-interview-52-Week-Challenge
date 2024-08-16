package com.safaricom.savings.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun <T> BottomNavigationBar(
    current: Int,
    items: List<T> = listOf(),
    onItemClicked: (T) -> Unit
) {
//    val sorted = items.sortedWith(compareBy { it.attributes?.weight })
    if (items.size < current) throw RuntimeException("Current index $current cannot be more than the items count(Size) which is ${items.size}")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        var position = 0
        items.forEach { item ->
            val isCurrent = position == current

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable {
                    onItemClicked.invoke(item)
                }
            ) {
                RegularSpacer()
                val color = if (isCurrent) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.onBackground

//                ImageView(
//                    if (isCurrent)
//                        item.attributes?.activeIcon?.data?.attributes?.iconUrl().orEmpty()
//                    else
//                        item.attributes?.inActiveIcon?.data?.attributes?.iconUrl().orEmpty(),
//                    modifier = Modifier.size(24.dp),
//                    tint = color
//                )
                RegularSpacer()
//                if (isCurrent) MediumTextView(item.attributes?.name, textColor = color)
//                else RegularTextView(item.attributes?.name, textColor = color)
                RegularSpacer()
            }

            position++
        }
    }
}