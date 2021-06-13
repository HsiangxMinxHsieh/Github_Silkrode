package util

import android.util.Log

fun logi(tag: String, log: Any) {

    Log.i(tag, log.toString())

}

fun loge(tag: String, log: Any, t: Throwable? = null) {
    if (t == null)
        Log.e(tag, log.toString())
    else
        Log.e(tag, log.toString(), t)

}

