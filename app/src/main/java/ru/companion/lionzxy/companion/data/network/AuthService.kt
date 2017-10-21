package ru.companion.lionzxy.companion.data.network

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.companion.lionzxy.companion.data.models.UserProfile


interface AuthService {
    @GET("auth")
    fun login(@Query("code") code: String): Observable<UserProfile>
}