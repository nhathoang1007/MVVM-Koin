package com.example.observableresearch.repository

import com.example.observableresearch.data.network.ApiGenerator
import com.example.observableresearch.data.storage.realm.IWorkoutDao
import com.example.observableresearch.model.Assignment
import com.example.observableresearch.model.Data
import io.reactivex.Observable

/**
 * Created by Nhat Vo on 14/06/2021.
 */
class WorkoutRepository constructor(
    private val service: ApiGenerator,
    private val dao: IWorkoutDao
) {

    fun loadWorkoutDataFromServer(): Observable<MutableList<Data>> {
        return service.createApi().getWorkoutData().map {
            it.data ?: mutableListOf()
        }
    }

    fun loadWorkoutFromLocalStorage(): Observable<MutableList<Data>> {
        return dao.getAll()
    }

    fun saveWorkoutToLocalStorage(data: MutableList<Data>): Observable<Boolean> {
        return dao.saveData(data)
    }

    fun updateAssignmentMark(data: Data): Observable<Boolean> {
        return dao.mark(data)
    }
}