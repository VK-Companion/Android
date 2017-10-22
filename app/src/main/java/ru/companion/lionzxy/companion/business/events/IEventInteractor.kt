package ru.companion.lionzxy.companion.business.events

import android.location.Location
import io.reactivex.Single
import ru.companion.lionzxy.companion.data.models.EventModel

interface IEventInteractor {
    fun setCurrentLocation(location: Location)
    fun getEvents(): Single<List<EventModel>>
}