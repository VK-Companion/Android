package ru.companion.lionzxy.companion.repositories.auth

interface IAuthRepository {
    fun getAuthtoken(): String
}