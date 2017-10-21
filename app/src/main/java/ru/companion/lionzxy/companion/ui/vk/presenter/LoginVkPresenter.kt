package ru.companion.lionzxy.companion.ui.vk.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.togezzer.android.utils.navigation.CompanionRouter
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.companion.lionzxy.companion.app.App
import ru.companion.lionzxy.companion.business.auth.IAuthInteractor
import ru.companion.lionzxy.companion.dagger.auth.AuthModule
import ru.companion.lionzxy.companion.ui.vk.view.LoginVkView
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class LoginVkPresenter : MvpPresenter<LoginVkView>() {
    @Inject
    lateinit var router: CompanionRouter

    @Inject
    lateinit var interactor: IAuthInteractor

    init {
        App.appComponent.plus(AuthModule()).inject(this)
    }

    fun onCodeRecieve(code: String) {
        viewState.hideWebview()
        interactor.auth(code)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    router.exitWithMessage(code)
                }, Timber::e)
    }
}