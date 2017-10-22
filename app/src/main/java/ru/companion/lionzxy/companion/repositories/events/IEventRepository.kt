package ru.companion.lionzxy.companion.repositories.events

import android.location.Location
import io.reactivex.Single
import ru.companion.lionzxy.companion.data.models.EventModel

interface IEventRepository {
    fun setCurrentLocation(location: Location)
    fun getEvents(): Single<List<EventModel>>}