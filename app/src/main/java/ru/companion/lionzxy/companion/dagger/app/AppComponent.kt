package ru.companion.lionzxy.companion.dagger.app

import dagger.Component
import ru.companion.lionzxy.companion.dagger.auth.AuthComponent
import ru.companion.lionzxy.companion.dagger.auth.AuthModule
import ru.companion.lionzxy.companion.ui.main.presenter.MainPresenter
import ru.companion.lionzxy.companion.ui.main.view.MainActivity
import ru.companion.lionzxy.companion.ui.login.view.LoginActivity
import ru.companion.lionzxy.companion.ui.splashscreen.view.SplashScreen
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class,
        NavigationModule::class))
@Singleton
interface AppComponent {
    fun inject(activity: SplashScreen)
    fun inject(activity: LoginActivity)
    fun inject(activity: MainActivity)
    fun inject(presenter: MainPresenter)

    fun plus(module: AuthModule): AuthComponent
}