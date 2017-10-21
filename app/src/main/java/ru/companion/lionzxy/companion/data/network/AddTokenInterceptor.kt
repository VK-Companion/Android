package ru.companion.lionzxy.companion.data.network

import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response
import ru.companion.lionzxy.companion.repositories.auth.AuthRepository
import timber.log.Timber

class AddTokenInterceptor(private val prefs: SharedPreferences) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = prefs.getString(AuthRepository.TOKEN_PREFS, null)
        var original = chain.request()

        if (token != null) {
            Timber.d("auth token=$token")
            val url = original
                    .url()
                    .newBuilder()
                    .addQueryParameter("token", token)
                    .build()
            original = original
                    .newBuilder()
                    .url(url)
                    .build()
        }
        return chain.proceed(original)
    }

}