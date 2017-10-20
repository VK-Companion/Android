package ru.companion.lionzxy.companion.dagger.auth

import dagger.Subcomponent
import ru.companion.lionzxy.companion.ui.splashscreen.presenter.SplashPresenter

@Subcomponent(modules = arrayOf(AuthModule::class))
@AuthScope
interface AuthComponent {
    fun inject(presenter: SplashPresenter)
}