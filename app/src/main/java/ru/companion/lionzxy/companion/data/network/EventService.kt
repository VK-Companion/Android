package ru.companion.lionzxy.companion.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.companion.lionzxy.companion.data.models.EventModel
import ru.companion.lionzxy.companion.data.models.ResponseObject

interface EventService {
    @GET("method/events.get")
    fun getEvent(@Query("lat") lat: Double, @Query("long") long: Double): Single<ResponseObject<List<EventModel>>>
}