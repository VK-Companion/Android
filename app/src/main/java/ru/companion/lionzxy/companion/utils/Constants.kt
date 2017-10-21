package ru.companion.lionzxy.companion.utils

import ru.companion.lionzxy.companion.BuildConfig

class Constants {
    companion object {
        const val FLOW_URL = "https://oauth.vk.com/authorize?client_id=6227861&display=mobile&redirect_uri=${BuildConfig.API_URL}auth&scope=5055514&response_type=code&v=5.68"
    }
}