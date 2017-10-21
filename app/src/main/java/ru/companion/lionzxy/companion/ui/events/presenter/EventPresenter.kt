package ru.companion.lionzxy.companion.ui.events.presenter

import android.graphics.Color
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.companion.lionzxy.companion.data.models.EventModel
import ru.companion.lionzxy.companion.ui.events.view.EventView
import timber.log.Timber
import java.util.*

@InjectViewState
class EventPresenter : MvpPresenter<EventView>() {
    fun loadEvent() {
        Single.just(listOf(EventModel(0, Color.BLUE, 1001, "Хакатон Вконтакте", "https://i.imgur.com/YpPw1hN.jpg", Date())))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.plusEvents(it)
                }, Timber::e)
    }
}