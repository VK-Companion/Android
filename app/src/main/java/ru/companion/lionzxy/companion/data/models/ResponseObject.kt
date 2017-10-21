package ru.companion.lionzxy.companion.data.models

import com.google.gson.annotations.SerializedName

class ResponseObject<T>(
        @SerializedName("response")
        var response: T)