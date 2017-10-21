package ru.companion.lionzxy.companion.utils

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

fun Activity.toast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG)
}

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View =
        LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)