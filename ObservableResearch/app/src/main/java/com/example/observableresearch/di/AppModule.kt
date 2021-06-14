package com.example.observableresearch.di

import com.example.observableresearch.data.network.ApiGenerator
import com.example.observableresearch.data.network.service.ServiceApi
import com.example.observableresearch.data.network.service.iServiceApi
import com.example.observableresearch.data.storage.realm.IWorkoutDao
import com.example.observableresearch.data.storage.realm.WorkoutDao
import com.example.observableresearch.repository.WorkoutRepository
import com.example.observableresearch.utils.SharedPrefs
import com.google.gson.Gson
import io.realm.Realm
import org.koin.dsl.module

val resourceModule = module {
    single { Gson() }
    single { SharedPrefs(get()) }
}

val dbModule = module {
    single<IWorkoutDao> { WorkoutDao() }
}

val retrofitModule = module {
    single<iServiceApi> { ServiceApi() }
    single { ApiGenerator(get()) }
}

val repositoryModule = module {
    single { WorkoutRepository(get(), get()) }
}
