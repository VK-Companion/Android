package ru.companion.lionzxy.companion.data.models

import com.google.gson.annotations.SerializedName

data class EventUser(
        @SerializedName("user_id")
        var id: Int,
        @SerializedName("first_name")
        var firstName: String,
        @SerializedName("last_name")
        var lastName: String,
        @SerializedName("photo")
        var photo: String,
        var reason: String)