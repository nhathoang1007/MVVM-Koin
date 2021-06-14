package com.example.observableresearch.view.test

import com.example.observableresearch.base.view.BaseView

/**
 * Created by Nhat Vo on 14/06/2021.
 */
interface TestView: BaseView<TestViewModel> {
    val mAdapter: WorkoutAdapter
}