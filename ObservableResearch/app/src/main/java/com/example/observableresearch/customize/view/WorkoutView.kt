package com.example.observableresearch.customize.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.databinding.ViewDataBinding
import com.example.observableresearch.databinding.CustomWorkoutViewBinding

/**
 * Created by Nhat Vo on 14/06/2021.
 */
class WorkoutView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : BaseCustomView(context, attrs, defStyle) {

    lateinit var binding: CustomWorkoutViewBinding

    override fun initViewDataBinging(inflater: LayoutInflater): ViewDataBinding {
        binding = CustomWorkoutViewBinding.inflate(inflater, this, true)
        return binding
    }
}