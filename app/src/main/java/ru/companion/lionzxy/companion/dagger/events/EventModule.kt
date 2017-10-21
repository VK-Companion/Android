package ru.companion.lionzxy.companion.dagger.events

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.companion.lionzxy.companion.business.events.EventInteractor
import ru.companion.lionzxy.companion.business.events.IEventInteractor
import ru.companion.lionzxy.companion.repositories.events.EventRepository
import ru.companion.lionzxy.companion.repositories.events.IEventRepository

@Module
class EventModule {

    @EventScope
    @Provides
    fun provideInteractor(repository: IEventRepository): IEventInteractor =
            EventInteractor(repository)

    @EventScope
    @Provides
    fun provideRepository(retrofit: Retrofit): IEventRepository =
            EventRepository(retrofit)

}