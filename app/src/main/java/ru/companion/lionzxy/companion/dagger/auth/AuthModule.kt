package ru.companion.lionzxy.companion.dagger.auth

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import ru.companion.lionzxy.companion.business.auth.AuthInteractor
import ru.companion.lionzxy.companion.business.auth.IAuthInteractor
import ru.companion.lionzxy.companion.repositories.auth.AuthRepository
import ru.companion.lionzxy.companion.repositories.auth.IAuthRepository

@Module
class AuthModule {

    @Provides
    @AuthScope
    fun provideAuthRepository(preference: SharedPreferences): IAuthRepository =
            AuthRepository(preference)

    @Provides
    @AuthScope
    fun provideAuthInteractor(repository: IAuthRepository): IAuthInteractor =
            AuthInteractor(repository)
}