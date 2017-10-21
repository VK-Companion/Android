package ru.companion.lionzxy.companion.ui.login.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.togezzer.android.utils.navigation.CompanionRouter
import io.reactivex.Completable
import ru.companion.lionzxy.companion.app.App
import ru.companion.lionzxy.companion.dagger.auth.AuthModule
import ru.companion.lionzxy.companion.ui.login.view.LoginView
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class LoginPresenter : MvpPresenter<LoginView>() {
    @Inject
    lateinit var router: CompanionRouter

    init {
        App.appComponent.plus(AuthModule()).inject(this)
    }

    fun onVkApiTokenGet(token: String) {
        viewState.showProgressDialog()
        Completable
                .complete()
                .subscribe({
                    viewState.hideProgressDialog()
                    router.openMainActivity()
                }, Timber::e)
    }
}