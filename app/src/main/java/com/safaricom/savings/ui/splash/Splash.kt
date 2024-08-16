package com.safaricom.savings.ui.splash

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.safaricom.savings.R
import com.safaricom.savings.ui.Routes
import com.safaricom.savings.ui.components.Center
import com.safaricom.savings.ui.components.ImageView
import com.safaricom.savings.ui.components.MediumSpacer
import com.safaricom.savings.ui.components.MediumTextView
import com.safaricom.savings.ui.components.SmallSpacer
import com.safaricom.savings.ui.util.LaunchedUnitEffect
import com.safaricom.savings.ui.util.modOf
import com.safaricom.savings.viewModels.AuthViewModel

@Composable
fun Splash(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {

    val isSignedIn by authViewModel.isSignedIn.observeAsState()

    if (isSignedIn == null)
        LaunchedUnitEffect {
            authViewModel.checkAuth()
        }
    else
        navController.navigate(if (isSignedIn == true) Routes.home else Routes.home)

    Center(modifier = modOf().fillMaxWidth()) {
        ImageView(R.drawable.ic_logo)
        MediumSpacer()
        MediumTextView(stringResource(R.string.app_name))
        SmallSpacer()
        LinearProgressIndicator()

    }
}