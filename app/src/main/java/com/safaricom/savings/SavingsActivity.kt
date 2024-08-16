package com.safaricom.savings

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.MutableLiveData
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
    private val dataIsOff = MutableLiveData(false)
    private val preferencesHelper: PreferencesHelper by inject()
    private lateinit var navController: NavHostController
    private lateinit var snackBarHostState: SnackbarHostState
    private lateinit var coroutineScope: CoroutineScope
    private var showToast = MutableLiveData(false)
    private var toastMessage = MutableLiveData("")

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
                        snackBarHostState = remember { SnackbarHostState() }
                        coroutineScope = rememberCoroutineScope()

                        if (relaunch == true) relaunchApp()

                        Column {
                            ChamaHubNavigation(
                                modOf().weight(1f),
                                navController,
                                preferencesHelper,
                                authViewModel,
                                savingsViewModel
                            )

                            if (showToast.value == true) {
                                Toast.makeText(
                                    this@SavingsActivity,
                                    toastMessage.value,
                                    Toast.LENGTH_SHORT
                                ).show()
                                showToast.postValue(false)
                            }
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