package ru.companion.lionzxy.companion.business.events

import android.location.Location

interface IEventInteractor {
    fun setCurrentLocation(location: Location)
}