package com.example.observableresearch.view.main

import androidx.core.widget.doAfterTextChanged
import com.example.observableresearch.R
import com.example.observableresearch.base.view.BaseActivity
import com.example.observableresearch.databinding.ActivityMainBinding
import com.example.observableresearch.databinding.TestBinding
import com.example.observableresearch.utils.MyHandler
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<TestBinding, MainViewModel>() {

    override val mViewModel: MainViewModel by viewModel()

    override fun getLayoutRes(): Int = R.layout.test

    override fun onStart() {
        super.onStart()
        MyHandler().postDelayed({
            mViewModel.testPrefs()
        }, 1000)
    }

    override fun initView() {
        super.initView()
        dataBinding.apply {
            iVew = this@MainActivity
            /*edtEmail.doAfterTextChanged {
                 mViewModel.onEmailChanged(it.toString())
             }
             edtPassword.doAfterTextChanged {
                 mViewModel.onPasswordChanged(it.toString())
             }*/
        }
    }
}