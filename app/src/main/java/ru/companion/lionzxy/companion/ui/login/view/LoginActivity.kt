package ru.companion.lionzxy.companion.ui.login.view

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import ru.companion.lionzxy.companion.R
import ru.companion.lionzxy.companion.ui.login.presenter.LoginPresenter

class LoginActivity : MvpAppCompatActivity(), LoginView {
    @InjectPresenter
    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //TODO
    }
}