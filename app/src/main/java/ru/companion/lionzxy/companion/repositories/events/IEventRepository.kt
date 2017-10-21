package ru.companion.lionzxy.companion.repositories.events

import android.location.Location

interface IEventRepository {
    fun setCurrentLocation(location: Location)
}