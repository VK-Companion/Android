package ru.companion.lionzxy.companion.app

import android.app.Application
import ru.companion.lionzxy.companion.dagger.app.AppComponent
import ru.companion.lionzxy.companion.dagger.app.AppModule
import ru.companion.lionzxy.companion.dagger.app.DaggerAppComponent

class App : Application() {
    companion object {
        @JvmStatic lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}