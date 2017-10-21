package ru.companion.lionzxy.companion.data.models

data class MessageModel(var id: Int,
                        var message: String,
                        var from: UserProfile){
    constructor(): this(0, "", UserProfile())
}