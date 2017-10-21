package ru.companion.lionzxy.companion.ui.main.view

import android.os.Bundle
import android.support.v4.app.Fragment
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.togezzer.android.utils.navigation.BaseNavigator
import com.togezzer.android.utils.navigation.CompanionRouter
import kotlinx.android.synthetic.main.activity_main.*
import ru.companion.lionzxy.companion.R
import ru.companion.lionzxy.companion.app.App
import ru.companion.lionzxy.companion.ui.main.presenter.MainPresenter
import ru.companion.lionzxy.companion.ui.profile.view.ProfileFragment
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {
    @InjectPresenter
    lateinit var presenter: MainPresenter

    @Inject
    lateinit var navigationHolder: NavigatorHolder

    private var navigator = object : BaseNavigator(this, R.id.content) {
        override fun createFragment(screenKey: String?, data: Any?): Fragment? {
            return when (screenKey) {
                CompanionRouter.Screens.FRAGMENT_PROFILE -> ProfileFragment()
                CompanionRouter.Screens.FRAGMENT_DIALOGS -> ProfileFragment()
                CompanionRouter.Screens.FRAGMENT_FEED -> ProfileFragment()
                else -> super.createFragment(screenKey, data)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.appComponent.inject(this)

        footer.setOnNavigationItemSelectedListener {
            val selectedItem = footer.menu.findItem(footer.selectedItemId)
            if (selectedItem?.itemId == it.itemId) {
                return@setOnNavigationItemSelectedListener true
            } else {
                return@setOnNavigationItemSelectedListener presenter.onNavigationItemSelected(it)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigationHolder.removeNavigator()
    }
}