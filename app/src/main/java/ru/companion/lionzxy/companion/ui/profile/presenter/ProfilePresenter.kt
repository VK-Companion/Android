package ru.companion.lionzxy.companion.ui.profile.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.companion.lionzxy.companion.data.models.UserProfile
import ru.companion.lionzxy.companion.ui.profile.view.ProfileView

@InjectViewState
class ProfilePresenter : MvpPresenter<ProfileView>() {
    fun loadUser() {
        viewState.onLoadUser(UserProfile(0, "Никита Куликов", "Хлебушек"), null)
    }
}