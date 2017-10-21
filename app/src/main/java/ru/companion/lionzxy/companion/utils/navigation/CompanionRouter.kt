package com.togezzer.android.utils.navigation

import ru.terrakok.cicerone.Router

class CompanionRouter : Router() {

    object Screens {
        const val SPLASH_ACTIVITY = "splash_activity"
        const val LOGIN_ACTIVITY = "login_activity"
        const val MAIN_ACTIVITY = "main_activity"
        const val LOGINVK_ACTIVITY = "vklogin_activity"

        const val FRAGMENT_FEED = "feed_fragment"
        const val FRAGMENT_DIALOGS = "dialogs_fragment"
        const val FRAGMENT_PROFILE = "profile_fragment"
    }

    fun openLoginScreen() {
        newRootScreen(Screens.LOGIN_ACTIVITY)
    }

    fun openMainActivity() {
        newRootScreen(Screens.MAIN_ACTIVITY)
    }

}