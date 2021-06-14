package com.example.observableresearch.data.network

import com.example.observableresearch.model.WorkoutResponse
import io.reactivex.Observable
import retrofit2.http.GET

@JvmSuppressWildcards
interface CallApi {
    @GET("workouts")
    fun getWorkoutData(): Observable<WorkoutResponse>
}
