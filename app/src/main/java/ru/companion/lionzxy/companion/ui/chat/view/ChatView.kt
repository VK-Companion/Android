package ru.companion.lionzxy.companion.ui.chat.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.companion.lionzxy.companion.data.models.MessageModel

interface ChatView : MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun onChatLoad(messages: List<MessageModel>)

    @StateStrategyType(SkipStrategy::class)
    fun clearInput()
}