package com.example.observableresearch.utils

import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.BindingAdapter
import com.example.observableresearch.extensions.isEnable
import io.reactivex.subjects.PublishSubject

object BindingUtils {
    @BindingAdapter("app:bindTo")
    @JvmStatic
    fun AppCompatButton.bindTo(subject: PublishSubject<Boolean>) {
        subject.subscribe(this.isEnable())
    }
}