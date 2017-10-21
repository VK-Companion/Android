package ru.companion.lionzxy.companion.ui.login.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.togezzer.android.utils.navigation.BaseNavigator
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback
import com.vk.sdk.VKSdk
import com.vk.sdk.api.VKError
import kotlinx.android.synthetic.main.activity_login.*
import ru.companion.lionzxy.companion.R
import ru.companion.lionzxy.companion.app.App
import ru.companion.lionzxy.companion.ui.login.presenter.LoginPresenter
import ru.companion.lionzxy.companion.utils.toast
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject


class LoginActivity : MvpAppCompatActivity(), LoginView {
    @InjectPresenter
    lateinit var presenter: LoginPresenter

    @Inject
    lateinit var navigationHolder: NavigatorHolder

    private var navigator = BaseNavigator(this, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        App.appComponent.inject(this)
        vk_login.setOnClickListener { VKSdk.login(this@LoginActivity) }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (VKSdk.onActivityResult(requestCode, resultCode, data, object : VKCallback<VKAccessToken> {
            override fun onResult(res: VKAccessToken) {
                presenter.onVkApiTokenGet(res.accessToken)
            }

            override fun onError(error: VKError) {
                toast(error.toString())
            }

        })) else {
            super.onActivityResult(requestCode, resultCode, data)
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

    override fun showProgressDialog() {
        content.visibility = View.GONE
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgressDialog() {
        content.visibility = View.VISIBLE
        progress_bar.visibility = View.GONE
    }

}