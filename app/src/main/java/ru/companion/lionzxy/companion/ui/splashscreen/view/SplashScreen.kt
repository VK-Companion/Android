package ru.companion.lionzxy.companion.ui.splashscreen.view

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.togezzer.android.utils.navigation.BaseNavigator
import ru.companion.lionzxy.companion.app.App
import ru.companion.lionzxy.companion.ui.splashscreen.presenter.SplashPresenter
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

class SplashScreen : MvpAppCompatActivity(), ISplashView {

    @InjectPresenter
    lateinit var presenter: SplashPresenter

    @Inject
    lateinit var navigationHolder: NavigatorHolder

    private var navigator = BaseNavigator(this, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        presenter.onLoad()
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
