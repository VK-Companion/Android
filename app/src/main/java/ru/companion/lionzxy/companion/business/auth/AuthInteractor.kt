package ru.companion.lionzxy.companion.business.auth

import io.reactivex.Single
import ru.companion.lionzxy.companion.data.models.UserProfile
import ru.companion.lionzxy.companion.repositories.auth.IAuthRepository

class AuthInteractor(private val repository: IAuthRepository) : IAuthInteractor {
    override fun isLoggedIn(): Boolean = !repository.getUser().token.isEmpty()
    override fun auth(code: String): Single<UserProfile> = repository.auth(code)
    override fun getUser(): UserProfile = repository.getUser()
}