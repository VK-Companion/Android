package ru.companion.lionzxy.companion.ui.events.presenter

import android.view.MenuItem
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.togezzer.android.utils.navigation.CompanionRouter
import ru.companion.lionzxy.companion.R
import ru.companion.lionzxy.companion.app.App
import ru.companion.lionzxy.companion.ui.events.view.MainView
import javax.inject.Inject

@InjectViewState
class MainPresenter: MvpPresenter<MainView>() {
    @Inject
    lateinit var router: CompanionRouter

    init {
        App.appComponent.inject(this)
    }

    fun openFragment(screenKey: String?) {
        router.replaceScreen(screenKey)
    }

    fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.feed -> openFragment(CompanionRouter.Screens.FRAGMENT_FEED)
            R.id.dialogs -> openFragment(CompanionRouter.Screens.FRAGMENT_DIALOGS)
            //TODO R.id.profile -> openFragment(TgzRouter.Screens.QUESTION_FRAGMENT)
            else -> {
                return false
            }
        }
        return true
    }
}