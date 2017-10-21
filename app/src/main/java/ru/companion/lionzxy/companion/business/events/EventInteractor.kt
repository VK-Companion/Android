package ru.companion.lionzxy.companion.business.events

import android.location.Location
import ru.companion.lionzxy.companion.repositories.events.IEventRepository

class EventInteractor(private val repository: IEventRepository) : IEventInteractor {
    override fun setCurrentLocation(location: Location) {
        repository.setCurrentLocation(location)
    }

}