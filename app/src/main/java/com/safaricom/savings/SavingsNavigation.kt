package com.safaricom.savings

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.safaricom.data.sharedPref.PreferencesHelper
import com.safaricom.savings.ui.Routes
import com.safaricom.savings.ui.home.Home
import com.safaricom.savings.ui.onboarding.OnBoarding
import com.safaricom.savings.ui.splash.Splash
import com.safaricom.savings.viewModels.AuthViewModel
import com.safaricom.savings.viewModels.SavingsViewModel

/**
 * Composable function that sets up the navigation graph for the savings application.
 *
 * This function defines the navigation routes and their associated composables for the app.
 * It includes routes for the splash screen, onboarding, and the home screen.
 *
 * @param modifier A [Modifier] to be applied to the NavHost. Defaults to [Modifier].
 * @param navController The [NavHostController] that controls navigation within the app.
 * @param preferencesHelper An instance of [PreferencesHelper] to manage user preferences.
 * @param authViewModel An instance of [AuthViewModel] that handles authentication logic.
 * @param savingsViewModel An instance of [SavingsViewModel] that handles the savings data.
 */

@Composable
fun SavingsNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    preferencesHelper: PreferencesHelper,
    authViewModel: AuthViewModel,
    savingsViewModel: SavingsViewModel
) {
    NavHost(
        navController,
        startDestination = Routes.splash,
        modifier = modifier
    ) {
        composable(Routes.splash) {
            Splash(navController, authViewModel)
        }
        composable(Routes.Auth.onboarding) {
            OnBoarding(navController, preferencesHelper, authViewModel)
        }
        composable(Routes.home) {
            Home(
                savingsViewModel
            )
        }
    }
}