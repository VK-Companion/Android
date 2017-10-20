package com.togezzer.android.utils.navigation

import ru.terrakok.cicerone.Router

class CompanionRouter : Router() {

    object Screens {
        const val SPLASH_ACTIVITY = "splash_activity"
        const val LOGIN_ACTIVITY = "login_activity"

    }

    fun openLoginScreen() {
        newRootScreen(Screens.LOGIN_ACTIVITY)
    }

    fun openMainActivity() {
        //TODO
        newRootScreen(Screens.SPLASH_ACTIVITY)
    }

}