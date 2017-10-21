package ru.companion.lionzxy.companion.ui.vk.view

import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.togezzer.android.utils.navigation.BaseNavigator
import kotlinx.android.synthetic.main.activity_flow.*
import ru.companion.lionzxy.companion.BuildConfig
import ru.companion.lionzxy.companion.R
import ru.companion.lionzxy.companion.app.App
import ru.companion.lionzxy.companion.ui.vk.presenter.LoginVkPresenter
import ru.companion.lionzxy.companion.utils.Constants
import ru.companion.lionzxy.companion.utils.splitQuery
import ru.terrakok.cicerone.NavigatorHolder
import java.net.URL
import javax.inject.Inject

class LoginVkActivity : MvpAppCompatActivity(), LoginVkView {

    @InjectPresenter
    lateinit var presenter: LoginVkPresenter

    @Inject
    lateinit var navigationHolder: NavigatorHolder

    private var navigator = BaseNavigator(this, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow)

        App.appComponent.inject(this)

        webView.loadUrl(Constants.FLOW_URL)
        webView.clearCache(true)
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                val url = request.url.toString()
                if (url.startsWith("${BuildConfig.API_URL}auth?code=")) {
                    val code = URL(url).splitQuery()["code"]!!
                    presenter.onCodeRecieve(code)
                } else {
                    view.loadUrl(url)
                }
                return true
            }
        }
    }

    override fun hideWebview() {
        progressBar.visibility = View.VISIBLE
        webView.visibility = View.GONE
    }

    override fun showWebview(url: String) {
        webView.loadUrl(url)
        progressBar.visibility = View.GONE
        webView.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigationHolder.removeNavigator()
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

}