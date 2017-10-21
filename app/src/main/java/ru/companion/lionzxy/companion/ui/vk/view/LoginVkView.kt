package ru.companion.lionzxy.companion.ui.vk.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface LoginVkView : MvpView {
    fun hideWebview()
    fun showWebview(url: String)
}