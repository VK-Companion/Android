package ru.companion.lionzxy.companion.dagger.events

import dagger.Subcomponent
import ru.companion.lionzxy.companion.ui.events.presenter.EventPresenter

@Subcomponent(modules = arrayOf(EventModule::class))
@EventScope
interface EventComponent {
    fun inject(presenter: EventPresenter)
}