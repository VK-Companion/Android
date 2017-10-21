package ru.companion.lionzxy.companion.dagger.messages

import dagger.Subcomponent
import ru.companion.lionzxy.companion.dagger.auth.AuthScope
import ru.companion.lionzxy.companion.ui.dialogs.presenter.DialogPresenter

@Subcomponent(modules = arrayOf(MessageModule::class))
@MessageScope
interface MessageComponent {
    fun inject(presenter: DialogPresenter)
}