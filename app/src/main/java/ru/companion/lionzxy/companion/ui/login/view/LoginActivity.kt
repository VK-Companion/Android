package ru.companion.lionzxy.companion.ui.login.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.togezzer.android.utils.navigation.BaseNavigator
import com.togezzer.android.utils.navigation.CompanionRouter
import kotlinx.android.synthetic.main.activity_login.*
import ru.companion.lionzxy.companion.R
import ru.companion.lionzxy.companion.app.App
import ru.companion.lionzxy.companion.ui.login.presenter.LoginPresenter
import ru.companion.lionzxy.companion.ui.vk.view.LoginVkActivity
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject


class LoginActivity : MvpAppCompatActivity(), LoginView {
    @InjectPresenter
    lateinit var presenter: LoginPresenter

    @Inject
    lateinit var navigationHolder: NavigatorHolder

    private var navigator = object : BaseNavigator(this, 0) {
        override fun createActivityIntent(screenKey: String?, data: Any?): Intent? {
            return when (screenKey) {
                CompanionRouter.Screens.LOGINVK_ACTIVITY -> {
                    Intent(this@LoginActivity, LoginVkActivity::class.java)
                }
                else -> super.createActivityIntent(screenKey, data)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        App.appComponent.inject(this)
        vk_login.setOnClickListener { presenter.openVkLogin() }
    }

    override fun onResume() {
        super.onResume()
        navigationHolder.setNavigator(navigator)
        presenter.checkAndOpen()
    }

    override fun onPause() {
        super.onPause()
        navigationHolder.removeNavigator()
    }

    override fun showProgressDialog() {
        content.visibility = View.GONE
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgressDialog() {
        content.visibility = View.VISIBLE
        progress_bar.visibility = View.GONE
    }

}