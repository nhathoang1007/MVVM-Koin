package com.example.observableresearch.application

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.example.observableresearch.di.resourceModule
import com.example.observableresearch.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp: MultiDexApplication() {

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MyApp)
            androidFileProperties()
            modules(listOf(resourceModule, viewModelModule))
        }
    }

    companion object {
        lateinit var instance: MyApp

        fun getApplicationContext(): Context = instance.applicationContext
    }
}