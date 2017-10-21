package ru.companion.lionzxy.companion.ui.chat.view

import com.arellomobile.mvp.MvpView
import ru.companion.lionzxy.companion.data.models.MessageModel

interface ChatView : MvpView {
    fun onChatLoad(messages: List<MessageModel>)
}