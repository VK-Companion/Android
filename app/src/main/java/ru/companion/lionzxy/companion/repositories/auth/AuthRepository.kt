package ru.companion.lionzxy.companion.repositories.auth

import android.content.SharedPreferences

class AuthRepository(private val preferences: SharedPreferences) : IAuthRepository {
    companion object {
        const val TOKEN_PREFS = "token"
    }

    override fun getAuthtoken(): String {
        return preferences.getString(TOKEN_PREFS, "")
    }

}