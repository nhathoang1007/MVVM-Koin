package com.example.observableresearch.base.view

import android.content.Context
import com.example.observableresearch.base.BaseViewModel
import com.example.observableresearch.base.viewmodel.DataState

interface BaseView<VM: BaseViewModel> {
    val mContext: Context
    val mViewModel: VM
    fun handelError(error: DataState.Failure)
    fun handelLoading(state: DataState.Loading)
}