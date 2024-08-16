package com.safaricom.savings

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.safaricom.data.sharedPref.PreferencesHelper
import com.safaricom.savings.theme.ChamaHubTheme
import com.safaricom.savings.ui.util.modOf
import com.safaricom.savings.viewModels.AuthViewModel
import com.safaricom.savings.viewModels.SavingsViewModel
import kotlinx.coroutines.CoroutineScope
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.koinViewModel
import java.util.Locale

@Suppress("DEPRECATION")
class SavingsActivity : ComponentActivity() {
    // Injects an instance of PreferencesHelper using Koin for dependency injection.
    private val preferencesHelper: PreferencesHelper by inject()
    // Lateinit variables for navigation and coroutine scope
    private lateinit var navController: NavHostController
    private lateinit var coroutineScope: CoroutineScope

    /**
     * Called when the activity is starting. This is where most initialization should go.
     * Components are initialized here including the navigation controller and coroutine scope.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down
     *                           then this Bundle contains the data it most recently supplied in `onSaveInstanceState`.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChamaHubTheme(preferencesHelper) {
                Scaffold { parentPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(parentPadding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val authViewModel = koinViewModel<AuthViewModel>()
                        val savingsViewModel = koinViewModel<SavingsViewModel>()

                        val relaunch by authViewModel.relaunchApp.observeAsState()

                        navController = rememberNavController()
                        coroutineScope = rememberCoroutineScope()

                        if (relaunch == true) relaunchApp()

                        Column {
                            SavingsNavigation(
                                modOf().weight(1f),
                                navController,
                                preferencesHelper,
                                authViewModel,
                                savingsViewModel
                            )
                        }
                    }
                }
            }
        }
        setLocale(preferencesHelper)
    }

    private fun relaunchApp() {
        val intent = Intent(this, SavingsActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        finish()
        startActivity(intent)
    }

    private fun setLocale(preferencesHelper: PreferencesHelper) {
        val l = preferencesHelper.language

        val locale = Locale(l)
        Locale.setDefault(locale)
        val config: Configuration = baseContext.resources.configuration
        config.locale = locale
        baseContext.resources.updateConfiguration(
            config,
            baseContext.resources.displayMetrics
        )
    }
}