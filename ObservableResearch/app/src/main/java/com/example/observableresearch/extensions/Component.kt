package com.example.observableresearch.extensions

import android.util.Log
import androidx.appcompat.widget.AppCompatButton
import com.example.observableresearch.R
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

fun AppCompatButton.isEnable(): Observer<Boolean> {
    return object : Observer<Boolean> {
        override fun onSubscribe(d: Disposable) {}

        override fun onNext(value: Boolean) {
            this@isEnable.apply {
                setBackgroundColor(if (value) {
                    R.color.purple_200
                } else {
                    R.color.black
                }.getColor())
            }
        }

        override fun onError(e: Throwable) {}

        override fun onComplete() {}
    }

}