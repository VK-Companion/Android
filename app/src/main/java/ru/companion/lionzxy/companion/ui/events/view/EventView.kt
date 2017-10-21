package ru.companion.lionzxy.companion.ui.events.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.companion.lionzxy.companion.data.models.EventModel


interface EventView : MvpView {
    @StateStrategyType(AddToEndStrategy::class)
    fun plusEvents(events: List<EventModel>)
}