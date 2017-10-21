package com.togezzer.android.utils.navigation

import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import ru.companion.lionzxy.companion.ui.main.view.MainActivity
import ru.companion.lionzxy.companion.ui.login.view.LoginActivity
import ru.terrakok.cicerone.android.SupportAppNavigator

open class BaseNavigator(private val activity: FragmentActivity, containerId: Int) : SupportAppNavigator(activity, containerId) {

    override fun createFragment(screenKey: String?, data: Any?): Fragment? {
        return null
    }

    override fun createActivityIntent(screenKey: String?, data: Any?): Intent? = when (screenKey) {
        CompanionRouter.Screens.MAIN_ACTIVITY -> {
            val intent = Intent(activity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent
        }
        CompanionRouter.Screens.LOGIN_ACTIVITY -> {
            val intent = Intent(activity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent
        }
        else -> null
    }

}