package ru.companion.lionzxy.companion.dagger.auth

import dagger.Subcomponent
import ru.companion.lionzxy.companion.ui.login.presenter.LoginPresenter
import ru.companion.lionzxy.companion.ui.profile.presenter.ProfilePresenter
import ru.companion.lionzxy.companion.ui.splashscreen.presenter.SplashPresenter
import ru.companion.lionzxy.companion.ui.vk.presenter.LoginVkPresenter

@Subcomponent(modules = arrayOf(AuthModule::class))
@AuthScope
interface AuthComponent {
    fun inject(presenter: SplashPresenter)
    fun inject(presenter: LoginPresenter)
    fun inject(presenter: ProfilePresenter)
    fun inject(presenter: LoginVkPresenter)
}