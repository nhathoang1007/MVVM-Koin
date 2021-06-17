package com.example.observableresearch.view.test

import androidx.lifecycle.Observer
import com.example.observableresearch.R
import com.example.observableresearch.base.view.BaseActivity
import com.example.observableresearch.databinding.ActivityTestBinding
import com.example.observableresearch.extensions.addDivider
import com.example.observableresearch.extensions.convertDpToPx
import com.google.gson.annotations.SerializedName
import org.koin.androidx.viewmodel.ext.android.viewModel

class TestActivity : BaseActivity<ActivityTestBinding, TestViewModel>(), TestView {

    override val mAdapter: WorkoutAdapter by lazy {
        WorkoutAdapter { data ->
            mViewModel.onMarkAssignment(data)
        }
    }

    override val mViewModel: TestViewModel by viewModel()

    override fun getLayoutRes(): Int = R.layout.activity_test

    override fun initView() {
        super.initView()
        dataBinding.apply {
            iView = this@TestActivity
            rvWorkout.addDivider(1.convertDpToPx())
        }
    }

    override fun initViewModel() {
        super.initViewModel()
        mViewModel.apply {
            dataObs.observe(this@TestActivity, Observer {
                mAdapter.updateData(it)
            })
        }
    }
}