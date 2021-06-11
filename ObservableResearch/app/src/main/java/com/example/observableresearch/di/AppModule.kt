package com.example.observableresearch.di

import com.example.observableresearch.data.network.ApiGenerator
import com.example.observableresearch.data.network.service.ServiceApi
import com.example.observableresearch.data.network.service.iServiceApi
import com.example.observableresearch.utils.SharedPrefs
import com.google.gson.Gson
import org.koin.dsl.module

val resourceModule = module {
    single { Gson() }
    single { SharedPrefs(get()) }
}

val RetrofitModule = module {
    single<iServiceApi> { ServiceApi() }
    single { ApiGenerator(get()) }
}