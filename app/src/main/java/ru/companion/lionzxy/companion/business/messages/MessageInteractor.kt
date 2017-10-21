package ru.companion.lionzxy.companion.business.messages

import ru.companion.lionzxy.companion.repositories.messages.IMessageRepository

class MessageInteractor(private val repository: IMessageRepository) : IMessageInteractor {
    override fun getDialogs() = repository.getDialogs()

}