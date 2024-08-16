package com.safaricom.savings.viewModels

import androidx.lifecycle.MutableLiveData
import com.safaricom.data.sharedPref.PreferencesHelper
import com.safaricom.data.utils.Languages
import kotlinx.coroutines.launch

class AuthViewModel(
    var preferencesHelper: PreferencesHelper
) : BaseViewModel() {
    var relaunchApp = MutableLiveData(false)
    var isSignedIn = MutableLiveData<Boolean>(null)
    var checkingAuth = MutableLiveData(false)
    var language = MutableLiveData(Languages.EN)

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