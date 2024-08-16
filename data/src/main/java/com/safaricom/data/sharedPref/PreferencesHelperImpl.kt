package com.safaricom.data.sharedPref

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.safaricom.data.utils.Languages
import java.io.File

/**
 * Implementation of the [PreferencesHelper] interface for managing shared preferences.
 *
 * This class provides methods to interact with shared preferences to store and retrieve user settings
 * and other persistent data.
 *
 * @param application The [Context] of the application, used to access the shared preferences.
 * @param sharedPreferencesFileName The name of the shared preferences file to be used.
 */
class PreferencesHelperImpl(
    private val application: Context,
    @PreferenceInfo sharedPreferencesFileName: String
) : PreferencesHelper {

    private var sharedPreferences: SharedPreferences

    init {
        val keyGenParameterSpec = KeyGenParameterSpec
            .Builder(
                MasterKey.DEFAULT_MASTER_KEY_ALIAS,
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
            )
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .setKeySize(MasterKey.DEFAULT_AES_GCM_MASTER_KEY_SIZE)
            .build()

        val masterKeyAlias = MasterKey.Builder(application)
            .setKeyGenParameterSpec(keyGenParameterSpec)
            .build()

        sharedPreferences = EncryptedSharedPreferences.create(
            application,
            sharedPreferencesFileName,
            masterKeyAlias,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    override var language: String
        get() = sharedPreferences.getString(Languages.PREF_KEY, Languages.EN)!!
        set(language) = sharedPreferences.edit().putString(Languages.PREF_KEY, language)
            .apply()

    override var fullName: String?
        get() = sharedPreferences.getString(PREF_FULLNAME, "")
        set(fullName) = sharedPreferences.edit().putString(PREF_FULLNAME, fullName).apply()

    override var email: String?
        get() = sharedPreferences.getString(PREF_EMAIL, "")
        set(email) = sharedPreferences.edit().putString(PREF_EMAIL, email).apply()

    override var msisdn: String?
        get() = sharedPreferences.getString(PREF_MSISDN, "")
        set(msisdn) = sharedPreferences.edit().putString(PREF_MSISDN, msisdn).apply()

    override var signedIn: Boolean?
        get() = sharedPreferences.getBoolean(PREF_SIGNED_IN, false)
        set(signedIn) = sharedPreferences.edit().putBoolean(PREF_SIGNED_IN, signedIn ?: false)
            .apply()

    @SuppressLint("ApplySharedPref")
    private fun clearPreference() {
        sharedPreferences.edit().clear().commit()
    }

    private fun clearAppData() {
        try {
            val cacheDirectory = application.cacheDir
            deleteCacheDirectory(cacheDirectory)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun logout() {
        clearPreference()
        clearAppData()
    }

    override fun isLoggedIn(): Boolean {
//        return !email.isNullOrEmpty() && !msisdn.isNullOrEmpty()
        return signedIn ?: false
    }

    override fun emailText() = if (email.isNullOrEmpty().not()) email.orEmpty() else "Not Available"

    private fun deleteCacheDirectory(cacheDirectory: File?): Boolean {
        return if (cacheDirectory != null && cacheDirectory.isDirectory) {
            val cacheFiles = cacheDirectory.list()
            cacheFiles?.indices?.forEach { i ->
                val success = deleteCacheDirectory(File(cacheDirectory, cacheFiles[i]))
                if (!success) {
                    return false
                }
            }
            cacheDirectory.delete()
        } else if (cacheDirectory != null && cacheDirectory.isFile) {
            cacheDirectory.delete()
        } else {
            false
        }
    }

    companion object {
        private const val PREF_FULLNAME = "PREF_FULLNAME"
        private const val PREF_MSISDN = "PREF_MSISDN"
        private const val PREF_EMAIL = "PREF_EMAIL"
        private const val PREF_SIGNED_IN = "PREF_SIGNED_IN"
    }
}
