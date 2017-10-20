package ru.companion.lionzxy.companion.ui.splashscreen.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.togezzer.android.utils.navigation.CompanionRouter
import ru.companion.lionzxy.companion.app.App
import ru.companion.lionzxy.companion.business.auth.IAuthInteractor
import ru.companion.lionzxy.companion.dagger.auth.AuthModule
import ru.companion.lionzxy.companion.ui.splashscreen.view.ISplashView
import javax.inject.Inject

@InjectViewState
class SplashPresenter : MvpPresenter<ISplashView>() {
    @Inject
    lateinit var router: CompanionRouter

    @Inject
    lateinit var authInteractor: IAuthInteractor

    init {
        App.appComponent.plus(AuthModule()).inject(this)
    }

    fun onLoad() {
        if (authInteractor.isLoggedIn()) {
            router.openMainActivity()
        } else {
            router.openLoginScreen()
        }
    }
}