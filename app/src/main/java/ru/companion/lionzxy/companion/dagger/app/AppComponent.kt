package ru.companion.lionzxy.companion.dagger.app

import dagger.Component
import ru.companion.lionzxy.companion.dagger.auth.AuthComponent
import ru.companion.lionzxy.companion.dagger.auth.AuthModule
import ru.companion.lionzxy.companion.ui.splashscreen.presenter.SplashPresenter
import ru.companion.lionzxy.companion.ui.splashscreen.view.SplashScreen
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class,
        NavigationModule::class))
@Singleton
interface AppComponent {
    fun inject(activity: SplashScreen)

    fun plus(module: AuthModule): AuthComponent
}