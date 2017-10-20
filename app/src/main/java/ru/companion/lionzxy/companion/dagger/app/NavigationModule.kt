package ru.companion.lionzxy.companion.dagger.app

import com.togezzer.android.utils.navigation.CompanionRouter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Singleton

@Module
class NavigationModule {
    private val cicerone: Cicerone<CompanionRouter> = Cicerone.create(CompanionRouter())

    @Singleton
    @Provides
    fun provideRouter(): CompanionRouter = cicerone.router

    @Singleton
    @Provides
    fun provideNavigationHolder(): NavigatorHolder = cicerone.navigatorHolder
}