package ru.companion.lionzxy.companion.repositories.events

import android.location.Location
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import ru.companion.lionzxy.companion.data.models.EventModel
import ru.companion.lionzxy.companion.data.network.EventService
import timber.log.Timber

class EventRepository(private val retrofit: Retrofit) : IEventRepository {
    val eventService: EventService = retrofit.create(EventService::class.java)
    var location: Location? = null

    override fun setCurrentLocation(location: Location) {
        this.location = location
    }

    override fun getEvents(): Single<List<EventModel>> {
        return Observable.fromCallable {
            while (location == null) {
                Timber.i("WAIT")
            }
        }.flatMap { eventService.getEvent(location!!.latitude, location!!.longitude).map { it.response }.toObservable() }
                .singleOrError()
                .doOnError {
                    Timber.e(it)
                }
                .subscribeOn(Schedulers.io())
    }

}