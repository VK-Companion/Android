package ru.companion.lionzxy.companion.utils

import android.app.Activity
import android.widget.Toast

fun Activity.toast(text: String){
    Toast.makeText(this, text, Toast.LENGTH_LONG)
}