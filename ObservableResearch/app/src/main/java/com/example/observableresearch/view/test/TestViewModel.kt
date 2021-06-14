package com.example.observableresearch.view.test

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.observableresearch.base.BaseViewModel
import com.example.observableresearch.extensions.observeOnUiThread
import com.example.observableresearch.model.Assignment
import com.example.observableresearch.model.Data
import com.example.observableresearch.repository.WorkoutRepository
import com.example.observableresearch.utils.Constants
import com.example.observableresearch.utils.observer.addTo
import io.reactivex.Observable
import java.text.SimpleDateFormat
import java.util.*

class TestViewModel(private val repository: WorkoutRepository) : BaseViewModel() {

    private val _dataObs = MutableLiveData<MutableList<Data>>()
    val dataObs: LiveData<MutableList<Data>>
        get() = _dataObs

    init {
        getData()
    }

    /**
     * Get start/end date by current day
     */
    @SuppressLint("SimpleDateFormat")
    private fun getDays(): Observable<MutableList<Data>> {
        return Observable.create { emitter ->
            val list = mutableListOf<Data>()
            val calendar = Calendar.getInstance()
            val monthBeginningCell = calendar[Calendar.DAY_OF_WEEK] - 2

            // move calendar backwards to the beginning of the week
            calendar.add(Calendar.DAY_OF_MONTH, -monthBeginningCell)
            val format = SimpleDateFormat(Constants.PATTERN_PARSE_TIME_SERVER)
            for (i in 0..6) {
                list.add(Data().apply {
                    timestamp = format.format(calendar.time)
                    day = i
                })
                calendar.add(Calendar.DATE, 1)
            }
            emitter.onNext(list)
            emitter.onComplete()
        }
    }

    private fun getData() {
        getDays().observeOnUiThread()
            .doOnSubscribe { setLoading(true) }
            .flatMap {
                _dataObs.postValue(it)
                repository.loadWorkoutFromLocalStorage()
                    .observeOnUiThread()
                    .map { local -> local.merge() }
            }.flatMap {
                _dataObs.postValue(it)
                repository.loadWorkoutDataFromServer()
                    .observeOnUiThread()
                    .map { response -> response.merge() }
            }.flatMap { list ->
                _dataObs.postValue(list)
                repository.saveWorkoutToLocalStorage(list)
            }.subscribe({
                setLoading(false)
            }, {
                setError(error = it)
            }).addTo(compositeDisposableBag)
    }

    private fun List<Data>.merge(): MutableList<Data> {
        val value = _dataObs.value ?: mutableListOf()

        value.forEach {
            this.find { f -> f.day == it.day }?.apply {
                it._id = _id
                it.assignments = assignments
            }
        }
        return value
    }

    fun onMarkAssignment(data: Data) {
        repository.updateAssignmentMark(data)
            .observeOnUiThread()
            .subscribe({}, {
                setError(error = it)
            }).addTo(compositeDisposableBag)
    }
}

