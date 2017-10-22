package ru.companion.lionzxy.companion.data.models

import android.graphics.Color
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class EventModel(
        @SerializedName("id")
        var id: Int,
        @SerializedName("distance")
        var distance: Long,
        @SerializedName("name")
        var name: String,
        @SerializedName("cover")
        var img_url: String?,
        @SerializedName("created")
        var date: Date,
        @SerializedName("photo_200")
        var alternativeUrl: String?,
        var color: Int = Random().nextInt()) : Serializable {
    constructor() : this(0, 0, "", "", Date(), null, Random().nextInt())
}