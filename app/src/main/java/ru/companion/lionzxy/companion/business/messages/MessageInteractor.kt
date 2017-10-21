package ru.companion.lionzxy.companion.business.messages

import io.reactivex.Completable
import ru.companion.lionzxy.companion.repositories.messages.IMessageRepository

class MessageInteractor(private val repository: IMessageRepository) : IMessageInteractor {

    override fun getDialogs() = repository.getDialogs()

    override fun getMessages(dialogId: Int) = repository.getMessages(dialogId)

    override fun sendMessage(message: String, dialogId: Int): Completable {
        return repository.sendMessage(message, dialogId)
    }

}