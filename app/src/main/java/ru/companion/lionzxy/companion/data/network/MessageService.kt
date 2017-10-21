package ru.companion.lionzxy.companion.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.companion.lionzxy.companion.data.models.DialogModel
import ru.companion.lionzxy.companion.data.models.MessageModel
import ru.companion.lionzxy.companion.data.models.ResponseObject


interface MessageService {
    @GET("method/messages.send")
    fun sendMessage(@Query("peer_id") to: Int,
                    @Query("text") message: String,
                    @Query("attachment") attachment: String = ""): Single<ResponseObject<Boolean>>

    @GET("method/messages.getDialogs")
    fun getDialogs(): Single<ResponseObject<List<DialogModel>>>

    @GET("method/messages.getDialogById")
    fun getDialog(@Query("peer_id") id: Int): Single<ResponseObject<List<MessageModel>>>
}