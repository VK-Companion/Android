package ru.companion.lionzxy.companion.repositories.auth

import io.reactivex.Single
import ru.companion.lionzxy.companion.data.models.UserProfile

interface IAuthRepository {
    fun getUser(): UserProfile
    fun auth(code: String): Single<UserProfile>

}