package ru.companion.lionzxy.companion.ui.profile.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.companion.lionzxy.companion.data.models.ActionModel
import ru.companion.lionzxy.companion.data.models.UserProfile

@StateStrategyType(AddToEndSingleStrategy::class)
interface ProfileView : MvpView {
    fun onLoadUser(userData: UserProfile, actions: List<ActionModel>?)
}