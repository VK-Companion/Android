package ru.companion.lionzxy.companion.repositories.auth

import android.content.SharedPreferences
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import ru.companion.lionzxy.companion.data.models.UserProfile
import ru.companion.lionzxy.companion.data.network.AuthService

class AuthRepository(private val preferences: SharedPreferences,
                     private val retrofit: Retrofit) : IAuthRepository {
    val authService: AuthService = retrofit.create(AuthService::class.java)

    companion object {
        const val TOKEN_PREFS = "token"
        const val USERID_PREFS = "userid"
        const val FNAME_PREFS = "first_name"
        const val LNAME_PREFS = "last_name"
        const val STATUS_PREFS = "status"
    }

    override fun getUser(): UserProfile {
        return UserProfile(preferences.getInt(USERID_PREFS, 0),
                preferences.getString(FNAME_PREFS, ""),
                preferences.getString(LNAME_PREFS, ""),
                preferences.getString(TOKEN_PREFS, ""),
                preferences.getString(STATUS_PREFS, ""))
    }

    override fun auth(code: String): Single<UserProfile> {
        return authService.login(code)
                .singleOrError()
                .subscribeOn(Schedulers.io())
                .doAfterSuccess { saveCurrentUser(it) }
    }

    private fun saveCurrentUser(user: UserProfile) {
        preferences
                .edit()
                .putString(TOKEN_PREFS, user.token)
                .putInt(USERID_PREFS, user.id)
                .putString(FNAME_PREFS, user.firstName)
                .putString(LNAME_PREFS, user.lastName)
                .putString(STATUS_PREFS, user.status)
                .apply()
    }

}