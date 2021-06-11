package com.example.observableresearch.extensions

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


fun <T> Observable<T>.observeOnUiThread(): Observable<T> =
    this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())