package com.example.observableresearch.base

import androidx.lifecycle.*
import com.example.observableresearch.base.viewmodel.DataState
import com.example.observableresearch.extensions.logError
import com.example.observableresearch.utils.observer.AutoDisposable

abstract class BaseViewModel : ViewModel(), LifecycleObserver {

    private val TAG = this::class.simpleName

    protected val _stateObs = MutableLiveData<DataState>()
    val stateObs: LiveData<DataState>
        get() = _stateObs

    protected val compositeDisposable: AutoDisposable by lazy {
        AutoDisposable()
    }

    fun setLoading(isLoading: Boolean) {
        TAG?.logError("$isLoading")
        _stateObs.postValue(DataState.Loading(isLoading = isLoading))
    }

    fun setError(message: String? = null, error: Throwable? = null) {
        _stateObs.postValue(DataState.Failure(message = message, t = error))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        compositeDisposable.onStop()
    }
}