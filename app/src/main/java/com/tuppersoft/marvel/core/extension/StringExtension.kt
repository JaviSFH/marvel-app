package com.tuppersoft.marvel.core.extension

import android.util.Log
import java.math.BigInteger
import java.security.MessageDigest

const val TAG = "MARVEL_TEST"

fun String.logd(tr: Throwable? = null) {
    Log.d(TAG, this, tr)
}

fun String.logi(tr: Throwable? = null) {
    Log.i(TAG, this, tr)
}

fun String.logv(tr: Throwable? = null) {
    Log.v(TAG, this, tr)
}

fun String.logw(tr: Throwable? = null) {
    Log.w(TAG, this, tr)
}

fun String.loge(tr: Throwable? = null) {
    Log.e(TAG, this, tr)
}

fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
}

