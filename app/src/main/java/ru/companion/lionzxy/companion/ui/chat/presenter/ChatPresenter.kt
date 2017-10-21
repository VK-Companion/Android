package ru.companion.lionzxy.companion.ui.chat.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.companion.lionzxy.companion.data.models.MessageModel
import ru.companion.lionzxy.companion.ui.chat.view.ChatView
import timber.log.Timber

@InjectViewState
class ChatPresenter : MvpPresenter<ChatView>() {
    fun loadMessage() {
        Single.just(listOf(MessageModel()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.onChatLoad(it)
                }, Timber::e)
    }
}