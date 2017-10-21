package ru.companion.lionzxy.companion.business.auth

import io.reactivex.Single
import ru.companion.lionzxy.companion.data.models.UserProfile

interface IAuthInteractor {
    fun isLoggedIn(): Boolean
    fun auth(code: String): Single<UserProfile>
    fun getUser(): UserProfile
}