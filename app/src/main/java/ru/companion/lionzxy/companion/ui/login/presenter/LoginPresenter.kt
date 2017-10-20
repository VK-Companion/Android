package ru.companion.lionzxy.companion.ui.login.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.Completable
import ru.companion.lionzxy.companion.ui.login.view.LoginView

@InjectViewState
class LoginPresenter : MvpPresenter<LoginView>() {
    fun onVkApiTokenGet(token: String) {
        viewState.showProgressDialog()
        Completable
                .complete()
                .doOnComplete {
                    viewState.hideProgressDialog()
                }
    }
}