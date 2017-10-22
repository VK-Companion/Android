package ru.companion.lionzxy.companion.ui.event_info.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.companion.lionzxy.companion.data.models.EventUser
import ru.companion.lionzxy.companion.ui.event_info.view.EventView

@InjectViewState
class EventPresenter: MvpPresenter<EventView>() {
    fun onInvite(user: EventUser){

    }
}