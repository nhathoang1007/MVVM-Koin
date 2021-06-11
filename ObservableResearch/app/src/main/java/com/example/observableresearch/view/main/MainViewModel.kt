package com.example.observableresearch.view.main

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.example.observableresearch.base.BaseViewModel
import com.example.observableresearch.data.storage.SharedKey
import com.example.observableresearch.extensions.observeOnUiThread
import com.example.observableresearch.utils.SharedPrefs
import com.example.observableresearch.utils.observer.addTo
import java.util.concurrent.TimeUnit

class MainViewModel(private val prefs: SharedPrefs): BaseViewModel() {

    init {
        prefs.put(SharedKey.TOKEN, "Jason")
    }

    fun testPrefs() {
        prefs.get(SharedKey.TOKEN, String::class.java)
            .observeOnUiThread()
            .doOnSubscribe { setLoading(true) }
            .doFinally { setLoading(false)}
            .subscribe({
                Log.e("Test", it)
            }, {
                Log.e("Test", "Error")
            }).addTo(compositeDisposable)
    }
}