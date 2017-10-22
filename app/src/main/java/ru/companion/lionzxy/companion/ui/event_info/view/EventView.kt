package ru.companion.lionzxy.companion.ui.event_info.view

import com.arellomobile.mvp.MvpView
import ru.companion.lionzxy.companion.data.models.EventUser

interface EventView : MvpView {
    fun loadUser(users: List<EventUser>)
}