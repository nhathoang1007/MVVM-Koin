package com.example.observableresearch.extensions

import android.content.Context
import com.example.observableresearch.application.MyApp

fun applicationContext(): Context {
    return MyApp.getApplicationContext()
}