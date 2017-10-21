package ru.companion.lionzxy.companion.ui.dialogs.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.togezzer.android.utils.navigation.CompanionRouter
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.companion.lionzxy.companion.app.App
import ru.companion.lionzxy.companion.business.messages.IMessageInteractor
import ru.companion.lionzxy.companion.dagger.messages.MessageModule
import ru.companion.lionzxy.companion.data.models.DialogModel
import ru.companion.lionzxy.companion.ui.dialogs.view.DialogView
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class DialogPresenter : MvpPresenter<DialogView>() {

    @Inject
    lateinit var router: CompanionRouter

    @Inject
    lateinit var messageInteractor: IMessageInteractor

    init {
        App.appComponent.plus(MessageModule()).inject(this)
    }

    fun loadDialogs() {
        messageInteractor
                .getDialogs()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.onLoadDialogs(it)
                }, Timber::e)
    }

    fun onDialogClick(dialogModel: DialogModel) {
        router.navigateTo(CompanionRouter.Screens.CHAT_ACTIVITY, dialogModel)
    }
}