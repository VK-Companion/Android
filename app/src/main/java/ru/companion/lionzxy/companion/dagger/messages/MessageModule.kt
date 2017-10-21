package ru.companion.lionzxy.companion.dagger.messages

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.companion.lionzxy.companion.business.messages.IMessageInteractor
import ru.companion.lionzxy.companion.business.messages.MessageInteractor
import ru.companion.lionzxy.companion.repositories.messages.IMessageRepository
import ru.companion.lionzxy.companion.repositories.messages.MessageRepository

@Module
class MessageModule {

    @Provides
    @MessageScope
    fun provideInteractor(repository: IMessageRepository): IMessageInteractor =
            MessageInteractor(repository)

    @Provides
    @MessageScope
    fun provideRepository(retrofit: Retrofit): IMessageRepository =
            MessageRepository(retrofit)
}