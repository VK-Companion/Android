package ru.companion.lionzxy.companion.ui.chat.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.companion.lionzxy.companion.app.App
import ru.companion.lionzxy.companion.business.messages.IMessageInteractor
import ru.companion.lionzxy.companion.dagger.messages.MessageModule
import ru.companion.lionzxy.companion.ui.chat.view.ChatView
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class ChatPresenter : MvpPresenter<ChatView>() {
    @Inject
    lateinit var interactor: IMessageInteractor

    init {
        App.appComponent.plus(MessageModule()).inject(this)
    }

    fun loadMessage(chatId: Int) {
        interactor.getMessages(chatId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.onChatLoad(it)
                }, Timber::e)
    }

    fun send(chatId: Int, message: String) {
        interactor.sendMessage(message, chatId)
                .toSingle { interactor.getMessages(chatId) }
                .flatMap { it }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.onChatLoad(it)
                    viewState.clearInput()
                }, Timber::e)
    }
}