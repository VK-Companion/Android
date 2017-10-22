package ru.companion.lionzxy.companion.data.models

import android.graphics.Color
import java.io.Serializable
import java.util.*

data class EventModel(var id: Int,
                      var color: Int,
                      var distance: Long,
                      var name: String,
                      var img_url: String,
                      var date: Date) : Serializable {
    constructor() : this(0, Color.CYAN, 0, "", "", Date())
}