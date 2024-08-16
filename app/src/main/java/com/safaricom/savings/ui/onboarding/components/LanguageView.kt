package com.safaricom.savings.ui.onboarding.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.safaricom.data.sharedPref.PreferencesHelper
import com.safaricom.data.utils.Languages
import com.safaricom.savings.R
import com.safaricom.savings.ui.onboarding.dialogs.LanguageConfirmationDialog
import com.safaricom.savings.ui.onboarding.dialogs.LanguageDialog
import com.safaricom.savings.viewModels.AuthViewModel

@Composable
fun LanguageView(
    preferencesHelper: PreferencesHelper,
    authViewModel: AuthViewModel
) {
    val language by authViewModel.language.observeAsState()
    val selectedLanguage = remember { mutableStateOf(language) }
    val changeLanguage = remember { mutableStateOf(false) }
    val changeLanguageConfirmation = remember { mutableStateOf(false) }

    SettingsItem(
        R.drawable.ic_language,
        stringResource(R.string.language),
        value = when (language) {
            Languages.SW -> stringResource(R.string.swahili)
            else -> stringResource(R.string.english)
        }
    ) {
        changeLanguage.value = true
    }

    if (changeLanguage.value)
        LanguageDialog(language ?: Languages.EN) {
            if (it != null) {
                changeLanguage.value = false
                changeLanguageConfirmation.value = true
                selectedLanguage.value = it
            } else {
                changeLanguage.value = false
            }
        }
    if (changeLanguageConfirmation.value)
        LanguageConfirmationDialog {
            preferencesHelper.language = selectedLanguage.value ?: Languages.EN
            authViewModel.relaunchApp.postValue(true)
            Thread.sleep(1000)
            changeLanguageConfirmation.value = false
        }
}