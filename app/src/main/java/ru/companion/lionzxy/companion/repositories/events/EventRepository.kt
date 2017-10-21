package ru.companion.lionzxy.companion.repositories.events

import android.location.Location
import retrofit2.Retrofit

class EventRepository(private val retrofit: Retrofit) : IEventRepository {
    override fun setCurrentLocation(location: Location) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}