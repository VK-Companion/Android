package ru.companion.lionzxy.companion.ui.event_info.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.companion.lionzxy.companion.data.models.EventUser

interface EventView : MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun loadUser(users: List<EventUser>)
}