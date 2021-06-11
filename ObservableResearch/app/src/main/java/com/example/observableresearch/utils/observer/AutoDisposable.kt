package com.example.observableresearch.utils.observer

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.blankj.utilcode.util.LogUtils
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class AutoDisposable {
    var compositeDisposable: CompositeDisposable? = null
    init {
        compositeDisposable = CompositeDisposable()
    }

    fun add(disposable: Disposable) {
        if (compositeDisposable != null) {
            compositeDisposable?.add(disposable)
        } else {
            throw NotImplementedError("must bind AutoDisposable to a Lifecycle first")
        }
    }

    fun onStop() {
        compositeDisposable?.dispose()
        LogUtils.e("Cancel this task success----->")
    }
}

fun Disposable.addTo(autoDisposable: AutoDisposable) {
    autoDisposable.add(this)
}