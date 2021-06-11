package com.example.observableresearch.extensions

import android.util.Log


fun String.logError(message: String) {
    Log.e(this, message)
}