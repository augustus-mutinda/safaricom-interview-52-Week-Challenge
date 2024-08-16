package com.safaricom.savings.ui.onboarding

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.safaricom.data.sharedPref.PreferencesHelper
import com.safaricom.data.utils.Languages
import com.safaricom.savings.R
import com.safaricom.savings.ui.Routes
import com.safaricom.savings.ui.components.Center
import com.safaricom.savings.ui.components.ImageView
import com.safaricom.savings.ui.components.MediumSpacer
import com.safaricom.savings.ui.components.MediumTextView
import com.safaricom.savings.ui.components.PrimaryButtonView
import com.safaricom.savings.ui.components.RegularSpacer
import com.safaricom.savings.ui.components.RegularTextView
import com.safaricom.savings.ui.components.SemiTextView
import com.safaricom.savings.ui.onboarding.dialogs.LanguageConfirmationDialog
import com.safaricom.savings.ui.onboarding.dialogs.LanguageDialog
import com.safaricom.savings.ui.util.IconSize
import com.safaricom.savings.ui.util.OnBackgroundColor
import com.safaricom.savings.ui.util.Padding
import com.safaricom.savings.ui.util.modOf
import com.safaricom.savings.viewModels.AuthViewModel

@Composable
fun OnBoarding(
    navHostController: NavHostController,
    preferencesHelper: PreferencesHelper,
    authViewModel: AuthViewModel
) {
    val showLanguageSelector = remember { mutableStateOf(false) }
    val changeLanguageConfirmation = remember { mutableStateOf(false) }
    val selectedLanguage = remember { mutableStateOf(preferencesHelper.language) }

    Center(modifier = modOf().padding(Padding.Medium)) {
        Spacer(modifier = modOf().weight(1f))
        SemiTextView(stringResource(R.string.app_name))
        Spacer(modifier = modOf().height(IconSize.Huge))
        ImageView(
            R.drawable.bg_onboarding,
            modOf()
                .height(IconSize.Banner)
                .fillMaxWidth()
        )
        Spacer(modifier = modOf().height(IconSize.Huge))
        RegularTextView(stringResource(R.string.tncs_onboarding), textAlign = TextAlign.Center)
        MediumSpacer()
        PrimaryButtonView(
            stringResource(R.string.get_started),
            modifier = modOf().fillMaxWidth()
        ) {
            navHostController.navigate(Routes.home)
        }
        MediumSpacer()
        RegularTextView(stringResource(R.string.learn_more))
        Spacer(modifier = modOf().weight(1f))
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = modOf().clickable {
                showLanguageSelector.value = true
            }
        ) {
            MediumTextView(stringResource(if (selectedLanguage.value == Languages.EN) R.string.english else R.string.swahili))
            RegularSpacer()
            ImageView(
                R.drawable.arrow_down,
                tint = OnBackgroundColor(),
                modifier = modOf()
                    .size(IconSize.Small)
                    .padding(Padding.Tiny)
            )
        }
        RegularSpacer()
    }

    if (showLanguageSelector.value)
        LanguageDialog(preferencesHelper.language) {
            showLanguageSelector.value = false
            if (it != null) {
                changeLanguageConfirmation.value = true
                selectedLanguage.value = it
            }
        }
    if (changeLanguageConfirmation.value)
        LanguageConfirmationDialog {
            preferencesHelper.language = selectedLanguage.value
            authViewModel.relaunchApp.postValue(true)
            Thread.sleep(1000)
            changeLanguageConfirmation.value = false
        }
}