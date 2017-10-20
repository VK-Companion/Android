package ru.companion.lionzxy.companion.app

import android.app.Application
import com.vk.sdk.VKSdk
import ru.companion.lionzxy.companion.R
import ru.companion.lionzxy.companion.app.handlers.AccessTokenHandler
import ru.companion.lionzxy.companion.dagger.app.AppComponent
import ru.companion.lionzxy.companion.dagger.app.AppModule
import ru.companion.lionzxy.companion.dagger.app.DaggerAppComponent
import timber.log.Timber
import com.vk.sdk.util.VKUtil



class App : Application() {
    companion object {
        @JvmStatic lateinit var appComponent: AppComponent
    }

    private val vkAccessTokenHandler = AccessTokenHandler()

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()

        vkAccessTokenHandler.startTracking()
        VKSdk.initialize(this)
    }
}