package ru.companion.lionzxy.companion.repositories.messages

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import ru.companion.lionzxy.companion.data.models.MessageModel
import ru.companion.lionzxy.companion.data.network.MessageService
import timber.log.Timber

class MessageRepository(retrofit: Retrofit) : IMessageRepository {

    private val messageService = retrofit.create(MessageService::class.java)

    override fun getDialogs() =
            messageService
                    .getDialogs()
                    .map { it.response }
                    .subscribeOn(Schedulers.io())

    override fun getMessages(dialogId: Int): Single<List<MessageModel>> {
        return messageService.getDialog(dialogId)
                .map { it.response }
                .subscribeOn(Schedulers.io())
    }


    override fun sendMessage(message: String, dialogId: Int): Completable {
        return messageService.sendMessage(dialogId, message)
                .subscribeOn(Schedulers.io())
                .doOnError {
                    Timber.e(it)
                }
                .toCompletable()
    }


}