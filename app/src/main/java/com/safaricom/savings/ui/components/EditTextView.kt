package com.safaricom.savings.ui.components

import android.view.KeyEvent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import com.safaricom.savings.ui.util.Padding
import com.safaricom.savings.ui.util.Radius
import com.safaricom.savings.ui.util.modOf

@Composable
fun EditText(
    value: String? = null,
    hint: String? = null,
    focusManager: FocusManager? = null,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    autoCorrect: Boolean = true,
    capitalization: KeyboardCapitalization = KeyboardCapitalization.Words,
    imeAction: ImeAction = ImeAction.Next,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = keyboardType,
        capitalization = capitalization,
        autoCorrect = autoCorrect,
        imeAction = imeAction
    ),
    onDone: (() -> Unit)? = null,
    onNext: (() -> Unit)? = null,
    keyboardActions: KeyboardActions = KeyboardActions(
        onNext = {
            onNext?.invoke() ?: focusManager?.moveFocus(FocusDirection.Down)
        },
        onDone = {
            onDone?.invoke()
        }
    ),
    maxLines: Int = 1,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    error: @Composable (() -> Unit)? = null,
    description: @Composable (() -> Unit)? = null,
    onTextChanged: (String?) -> Unit
) {
    if (focusManager != null)
        modifier.onPreviewKeyEvent {
            if (it.nativeKeyEvent.action == KeyEvent.ACTION_DOWN) {
                focusManager.moveFocus(FocusDirection.Down)
                true
            } else false
        }

    OutlinedTextField(
        value.orEmpty(),
        modifier = modifier,
        maxLines = maxLines,
        leadingIcon = prefix,
        trailingIcon = suffix,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        shape = RoundedCornerShape(Radius.Card),
        label = { RegularTextView(hint) },
        onValueChange = {
            onTextChanged.invoke(it)
        }
    )

    if (error != null) error()
    if (description != null) description()
}

@Composable
fun EditTextError(error: String) {
    SmallTextView(
        error,
        textColor = MaterialTheme.colorScheme.error,
        modifier = modOf().padding(start = Padding.Regular)
    )
}

@Composable
fun EditTextDescription(description: String) {
    SmallTextView(
        description,
        modifier = modOf().padding(start = Padding.Regular)
    )
}