package ru.companion.lionzxy.companion.ui.profile.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.companion.lionzxy.companion.app.App
import ru.companion.lionzxy.companion.business.auth.IAuthInteractor
import ru.companion.lionzxy.companion.dagger.auth.AuthModule
import ru.companion.lionzxy.companion.ui.profile.view.ProfileView
import javax.inject.Inject

@InjectViewState
class ProfilePresenter : MvpPresenter<ProfileView>() {

    @Inject
    lateinit var interactor: IAuthInteractor

    init {
        App.appComponent.plus(AuthModule()).inject(this)
    }

    fun loadUser() {
        viewState.onLoadUser(interactor.getUser(), null)
    }
}