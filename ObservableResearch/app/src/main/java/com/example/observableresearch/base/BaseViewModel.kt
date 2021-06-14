package com.example.observableresearch.base

import androidx.lifecycle.*
import com.example.observableresearch.base.viewmodel.DataState
import com.example.observableresearch.extensions.logError
import com.example.observableresearch.utils.observer.DisposableBag
import io.reactivex.subjects.BehaviorSubject

abstract class BaseViewModel : ViewModel(), LifecycleObserver {

    private val TAG = this::class.simpleName

    val data = BehaviorSubject.create<String>()

    protected val _stateObs = MutableLiveData<DataState>()
    val stateObs: LiveData<DataState>
        get() = _stateObs

    protected val compositeDisposableBag: DisposableBag by lazy {
        DisposableBag()
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
        compositeDisposableBag.onStop()
    }
}