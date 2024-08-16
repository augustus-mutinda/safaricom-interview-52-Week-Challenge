package com.safaricom.data.sharedPref

interface PreferencesHelper {
    var language: String
    var msisdn: String?
    var fullName: String?
    var email: String?

    fun isLoggedIn(): Boolean
    fun logout()
    fun emailText(): String
}
