package ru.companion.lionzxy.companion.business.auth

import ru.companion.lionzxy.companion.repositories.auth.IAuthRepository

class AuthInteractor(private val repository: IAuthRepository): IAuthInteractor {
    override fun isLoggedIn(): Boolean = !repository.getAuthtoken().isEmpty()
}