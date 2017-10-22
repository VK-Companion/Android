package ru.companion.lionzxy.companion.ui.event_info.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.companion.lionzxy.companion.app.App
import ru.companion.lionzxy.companion.business.events.IEventInteractor
import ru.companion.lionzxy.companion.dagger.events.EventModule
import ru.companion.lionzxy.companion.data.models.EventUser
import ru.companion.lionzxy.companion.ui.event_info.view.EventView
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class EventPresenter : MvpPresenter<EventView>() {
    @Inject
    lateinit var interactor: IEventInteractor

    init {
        App.appComponent.plus(EventModule()).inject(this)
    }

    fun onInvite(user: EventUser) {
    }

    fun onLoad() {
        Single.just(listOf(EventUser(0, "Олег", "Васильев", "https://pp.userapi.com/c637316/v637316913/4220c/Ro1jYZ9nzXE.jpg", "Учиться В МГТУ им. Баумана"),
                EventUser(0, "Екатерина", "Серова", "https://pp.userapi.com/c837520/v837520394/5a250/hyTD3UG50jA.jpg", "Подписана на \"Подслушано Москва\""),
                EventUser(0, "Влад", "Бусов", "https://pp.userapi.com/c638124/v638124608/14250/lKxuGEZF3W4.jpg", "Любит Noize MC"),
                EventUser(0, "Мартин", "Разроев", "https://pp.userapi.com/c836222/v836222184/5d0ec/XkmfM1f0EiU.jpg", "Работает в \"Improv Russia\""),
                EventUser(0, "Юрий", "Голубев", "https://pp.userapi.com/c840227/v840227490/1685e/VnmVnIi78sk.jpg", "Подписан на \"Хабрахабр\"")))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.loadUser(it)
                }, Timber::e)
    }
}