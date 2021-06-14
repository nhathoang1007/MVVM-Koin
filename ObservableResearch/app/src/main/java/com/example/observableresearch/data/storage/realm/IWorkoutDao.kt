package com.example.observableresearch.data.storage.realm

import com.example.observableresearch.model.Data
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * Created by NhiNguyen on 4/16/2020.
 */

interface IWorkoutDao {
    fun saveData(data: MutableList<Data>): Observable<Boolean>
    fun mark(data: Data): Observable<Boolean>
    fun getAll(): Observable<MutableList<Data>>
}