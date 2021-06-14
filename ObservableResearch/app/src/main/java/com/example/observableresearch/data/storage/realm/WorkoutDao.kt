package com.example.observableresearch.data.storage.realm

import com.example.observableresearch.extensions.getDefault
import com.example.observableresearch.model.Data
import io.reactivex.Observable
import io.realm.Realm
import io.realm.RealmList
import java.lang.Exception

/**
 * Created by NhiNguyen on 4/16/2020.
 */

class WorkoutDao : IWorkoutDao {
    override fun saveData(data: MutableList<Data>): Observable<Boolean> {
        return Observable.create { emitter ->
            Realm.getDefaultInstance().use {
                it.executeTransactionAsync { realm ->
                    realm.insertOrUpdate(data)
                    emitter.onNext(true)
                    emitter.onComplete()
                }
            }
        }
    }

    override fun getAll(): Observable<MutableList<Data>> {
        return Observable.create { emitter ->
            Realm.getDefaultInstance().use { realm ->
                realm.executeTransactionAsync {
                    val realmResults = it.where(Data::class.java).findAll()
                    emitter.onNext(it.copyFromRealm(realmResults))
                    emitter.onComplete()
                }
            }
        }
    }

    override fun mark(data: Data): Observable<Boolean> {
        return Observable.create { emitter ->
            Realm.getDefaultInstance().use {
                it.executeTransactionAsync { realm ->
                    // find the item
                    val item = realm.where(Data::class.java)
                        .equalTo(ID, data._id).findFirst()
                    item?.apply {
                        day = data.day.getDefault()
                        timestamp = data.timestamp?.getDefault()
                        assignments = data.assignments
                    }
                    emitter.onNext(true)
                    emitter.onComplete()
                }
            }
        }
    }

    companion object {
        private const val ID = "_id"
    }
}