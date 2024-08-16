package com.safaricom.savings.viewModels

import androidx.lifecycle.MutableLiveData
import com.safaricom.data.sharedPref.PreferencesHelper
import com.safaricom.data.utils.Languages
import kotlinx.coroutines.launch

/**
 * ViewModel class responsible for handling authentication-related data and operations.
 *
 * This ViewModel manages authentication state, user login status, and app language preferences.
 * It communicates with the [PreferencesHelper] to check if the user is signed in.
 *
 * @property preferencesHelper An instance of [PreferencesHelper] used to access user preferences.
 */
class AuthViewModel(
    var preferencesHelper: PreferencesHelper
) : BaseViewModel() {
    var relaunchApp = MutableLiveData(false)
    var isSignedIn = MutableLiveData<Boolean>(null)
    var checkingAuth = MutableLiveData(false)
    var language = MutableLiveData(Languages.EN)

    /**
     * Checks if the user is authenticated by querying the [PreferencesHelper].
     * This method simulates a network call with a delay.
     * The authentication check is only performed if it's not already in progress.
     */
    fun checkAuth() {
        if (checkingAuth.value == false) {
            checkingAuth.value = true
            bgScope.launch {
                // Sleep to mimic network call
                Thread.sleep(1000)
                isSignedIn.postValue(preferencesHelper.isLoggedIn())
            }
        }
    }
}