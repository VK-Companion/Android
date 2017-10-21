package ru.companion.lionzxy.companion.repositories.messages

import io.reactivex.Single
import ru.companion.lionzxy.companion.data.models.DialogModel

interface IMessageRepository {
    fun getDialogs(): Single<List<DialogModel>>
}