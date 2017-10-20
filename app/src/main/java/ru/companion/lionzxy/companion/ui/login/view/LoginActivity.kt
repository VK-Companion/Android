package ru.companion.lionzxy.companion.ui.login.view

import android.app.ProgressDialog
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback
import com.vk.sdk.VKSdk
import com.vk.sdk.api.VKError
import kotlinx.android.synthetic.main.activity_login.*
import ru.companion.lionzxy.companion.R
import ru.companion.lionzxy.companion.ui.login.presenter.LoginPresenter
import ru.companion.lionzxy.companion.utils.toast



class LoginActivity : MvpAppCompatActivity(), LoginView {
    @InjectPresenter
    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        vk_login.setOnClickListener { VKSdk.login(this@LoginActivity) }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, object : VKCallback<VKAccessToken> {
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

    override fun showProgressDialog() {
        content.visibility = View.GONE
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgressDialog() {
        content.visibility = View.VISIBLE
        progress_bar.visibility = View.GONE
    }

}