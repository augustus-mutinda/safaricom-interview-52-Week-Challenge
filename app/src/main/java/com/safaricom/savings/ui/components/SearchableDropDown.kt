package com.safaricom.savings.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.unit.dp
import com.safaricom.savings.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> DropDown(
    modifier: Modifier = Modifier,
    label: String,
    items: List<T>,
    itemExpandedHandler: (T?) -> String,
    itemCollapsedHandler: (T?) -> String = itemExpandedHandler,
    background: Color = MaterialTheme.colorScheme.surface,
    onChange: (T?) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember {
        mutableStateOf(
            if (items.isEmpty()) null else {
                onChange.invoke(items[0])
                items[0]
            }
        )
    }

    val shape = if (expanded) RoundedCornerShape(8.dp).copy(
        bottomEnd = CornerSize(0.dp),
        bottomStart = CornerSize(0.dp)
    )
    else RoundedCornerShape(8.dp)

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        TextField(
            modifier = modifier.menuAnchor(),
            textStyle = MaterialTheme.typography.bodyMedium,
            readOnly = true,
            value = if (expanded) itemExpandedHandler(selectedOptionText)
            else itemCollapsedHandler(selectedOptionText),
            onValueChange = {
                onChange.invoke(selectedOptionText)
            },
            label = { LabelTextView(text = label) },
            trailingIcon = {
                if (!expanded) ImageView(
                    R.drawable.ic_chevron_down,
                    modifier = Modifier.size(18.dp)
                )
                else ImageView(R.drawable.ic_chevron_up, modifier = Modifier.size(18.dp))
            },
            shape = shape,
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                focusedContainerColor = background,
                unfocusedContainerColor = background,
                focusedIndicatorColor = Transparent,
                unfocusedIndicatorColor = Transparent
            )
        )
        ExposedDropdownMenu(
            expanded = expanded,
            modifier = modifier
                .background(background)
                .fillMaxWidth(),
            onDismissRequest = { expanded = false },
        ) {
            items.forEach { selectionOption ->
                DropdownMenuItem(
                    text = {
                        RegularTextView(
                            text =
                            if (expanded) itemExpandedHandler(selectedOptionText)
                            else itemCollapsedHandler(selectedOptionText)
                        )
                    },
                    onClick = {
                        selectedOptionText = selectionOption
                        expanded = false
                        onChange.invoke(selectionOption)
                    },
                    modifier = modifier
                        .background(background)
                        .fillMaxWidth(),
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}