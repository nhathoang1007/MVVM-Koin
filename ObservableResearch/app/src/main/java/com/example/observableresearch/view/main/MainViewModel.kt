package com.example.observableresearch.view.main

import android.util.Log
import com.example.observableresearch.base.BaseViewModel
import com.example.observableresearch.data.storage.SharedKey
import com.example.observableresearch.extensions.bindTo
import com.example.observableresearch.extensions.observeOnUiThread
import com.example.observableresearch.utils.SharedPrefs
import com.example.observableresearch.utils.observer.addTo
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class MainViewModel(private val prefs: SharedPrefs) : BaseViewModel() {

    private val emailObs = PublishSubject.create<String>()
    private val passwordObs = PublishSubject.create<String>()

    val isEnableConfirm = PublishSubject.create<Boolean>()

    init {
        prefs.put(SharedKey.TOKEN, "Jason")

        Observable.combineLatest(emailObs, passwordObs) { email, password ->
            email.isNotEmpty() && password.isNotEmpty()
        }.bindTo(isEnableConfirm).addTo(compositeDisposableBag)
    }

    fun onEmailChanged(email: String) {
        emailObs.onNext(email)
    }

    fun onPasswordChanged(password: String) {
        passwordObs.onNext(password)
    }

    fun testPrefs() {
        prefs.get(SharedKey.TOKEN, String::class.java)
            .observeOnUiThread()
            .doOnSubscribe { setLoading(true) }
            .doFinally { setLoading(false) }
            .subscribe({
                Log.e("Test", it)
            }, {
                Log.e("Test", "Error")
            }).addTo(compositeDisposableBag)
    }
}

