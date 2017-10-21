package ru.companion.lionzxy.companion.ui.dialogs.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.companion.lionzxy.companion.data.models.DialogModel

@StateStrategyType(AddToEndSingleStrategy::class)
interface DialogView : MvpView {
    fun onLoadDialogs(dialogs: List<DialogModel>)
}