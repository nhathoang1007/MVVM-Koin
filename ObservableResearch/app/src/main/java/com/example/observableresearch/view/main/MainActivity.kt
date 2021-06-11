package com.example.observableresearch.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.observableresearch.R
import com.example.observableresearch.base.view.BaseActivity
import com.example.observableresearch.base.viewmodel.DataState
import com.example.observableresearch.data.storage.SharedKey
import com.example.observableresearch.databinding.ActivityMainBinding
import com.example.observableresearch.extensions.observeOnUiThread
import com.example.observableresearch.utils.MyHandler
import com.example.observableresearch.utils.SharedPrefs
import com.example.observableresearch.utils.observer.AutoDisposable
import com.example.observableresearch.utils.observer.addTo
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val mViewModel: MainViewModel by viewModel()

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun onStart() {
        super.onStart()
        MyHandler().postDelayed({
            mViewModel.testPrefs()
        }, 1000)
    }
}