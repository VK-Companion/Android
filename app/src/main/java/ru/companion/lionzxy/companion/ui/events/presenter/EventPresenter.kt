package ru.companion.lionzxy.companion.ui.events.presenter

import android.content.Context
import android.graphics.Color
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.nlopez.smartlocation.SmartLocation
import io.nlopez.smartlocation.rx.ObservableFactory
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ru.companion.lionzxy.companion.app.App
import ru.companion.lionzxy.companion.business.events.IEventInteractor
import ru.companion.lionzxy.companion.dagger.events.EventModule
import ru.companion.lionzxy.companion.data.models.EventModel
import ru.companion.lionzxy.companion.ui.events.view.EventView
import timber.log.Timber
import java.util.*
import javax.inject.Inject

@InjectViewState
class EventPresenter : MvpPresenter<EventView>() {

    @Inject
    lateinit var eventInteractor: IEventInteractor

    private val subscription = CompositeDisposable()

    init {
        App.appComponent.plus(EventModule()).inject(this)
    }

    fun loadEvent() {
        Single.just(listOf(EventModel(0, Color.BLUE, 1001, "Хакатон Вконтакте", "https://i.imgur.com/YpPw1hN.jpg", Date())))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.plusEvents(it)
                }, Timber::e)
    }

    fun subscribeToLocation(context: Context) {
        subscription.add(ObservableFactory.from(SmartLocation.with(context).location()).subscribe({
            eventInteractor.setCurrentLocation(it)
        }, Timber::e))
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}