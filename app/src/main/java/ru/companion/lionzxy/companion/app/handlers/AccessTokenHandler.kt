package ru.companion.lionzxy.companion.app.handlers

import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKAccessTokenTracker
import timber.log.Timber

class AccessTokenHandler: VKAccessTokenTracker(){
    override fun onVKAccessTokenChanged(oldToken: VKAccessToken?, newToken: VKAccessToken?) {
        Timber.e("Ощибка")
    }
}