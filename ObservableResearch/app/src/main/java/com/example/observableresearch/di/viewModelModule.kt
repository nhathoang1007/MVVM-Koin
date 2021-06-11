package com.example.observableresearch.di

import com.example.observableresearch.view.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule  = module {
    viewModel { MainViewModel(get()) }
}