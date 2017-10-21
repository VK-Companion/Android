package ru.companion.lionzxy.companion.repositories.messages

import io.reactivex.Completable
import io.reactivex.Single
import ru.companion.lionzxy.companion.data.models.DialogModel
import ru.companion.lionzxy.companion.data.models.MessageModel

interface IMessageRepository {
    fun getDialogs(): Single<List<DialogModel>>
    fun getMessages(dialogId: Int): Single<List<MessageModel>>
    fun sendMessage(message: String, dialogId: Int): Completable
}