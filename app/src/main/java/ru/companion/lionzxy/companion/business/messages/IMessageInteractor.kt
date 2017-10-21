package ru.companion.lionzxy.companion.business.messages

import io.reactivex.Single
import ru.companion.lionzxy.companion.data.models.DialogModel

interface IMessageInteractor {
    fun getDialogs(): Single<List<DialogModel>>
}