package ru.companion.lionzxy.companion.utils

import android.app.Activity
import android.content.Context
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import ru.companion.lionzxy.companion.R
import java.io.UnsupportedEncodingException
import java.net.URL
import java.net.URLDecoder
import java.util.*


fun Activity.toast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG)
}

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View =
        LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)

@Throws(UnsupportedEncodingException::class)
fun URL.splitQuery(): Map<String, String> {
    val queryPairs = LinkedHashMap<String, String>()
    val query = this.query
    val pairs = query.split("&".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
    for (pair in pairs) {
        val idx = pair.indexOf("=")
        queryPairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"))
    }
    return queryPairs
}

fun Date.toDay(): Int = Integer.parseInt(DateFormat.format("dd", this) as String)


fun Date.toMonth(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar.get(Calendar.MONTH)
}

fun Date.toMonthString(c: Context): String = c.resources.getStringArray(R.array.months_name)[toMonth()]