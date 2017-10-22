package ru.companion.lionzxy.companion.data.models

import com.google.gson.annotations.SerializedName

data class MessageModel(
        @SerializedName("sender_id")
        var id: Int,
        @SerializedName("text")
        var message: String,
        @SerializedName("user_sender")
        var from: DialogUser,
        @SerializedName("attachments")
        var attachment: String) {
    constructor() : this(0, "", DialogUser(), "")
}