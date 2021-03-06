package ru.companion.lionzxy.companion.ui.events.presenter

import android.content.Context
import android.graphics.Color
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.togezzer.android.utils.navigation.CompanionRouter
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

    @Inject
    lateinit var router: CompanionRouter

    private val subscription = CompositeDisposable()

    init {
        App.appComponent.plus(EventModule()).inject(this)
    }

    fun loadEvent() {
        eventInteractor.getEvents()
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

    fun openEvent(model: EventModel) {
        router.navigateTo(CompanionRouter.Screens.EVENT_ACTIVITY, model)
    }

    override fun onDestroy() {
        super.onDestroy()
        subscription.clear()
    }
}