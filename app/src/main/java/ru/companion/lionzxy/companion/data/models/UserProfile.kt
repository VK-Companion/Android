package ru.companion.lionzxy.companion.data.models

import com.google.gson.annotations.SerializedName

data class UserProfile(
        @SerializedName("user_id")
        var id: Int,
        @SerializedName("first_name")
        var firstName: String,
        @SerializedName("last_name")
        var lastName: String,
        @SerializedName("token")
        var token: String,
        @SerializedName("status")
        var status: String = "") {
    constructor() : this(0, "", "", "")
}