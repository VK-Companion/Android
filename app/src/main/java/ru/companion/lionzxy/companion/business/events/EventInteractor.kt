package ru.companion.lionzxy.companion.business.events

import android.location.Location
import io.reactivex.Single
import ru.companion.lionzxy.companion.data.models.EventModel
import ru.companion.lionzxy.companion.repositories.events.IEventRepository

class EventInteractor(private val repository: IEventRepository) : IEventInteractor {
    override fun getEvents(): Single<List<EventModel>> {
        return repository.getEvents()
    }

    override fun setCurrentLocation(location: Location) {
        repository.setCurrentLocation(location)
    }

}