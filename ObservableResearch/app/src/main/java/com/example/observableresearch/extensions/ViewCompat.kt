package com.example.observableresearch.extensions

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.observableresearch.R
import com.example.observableresearch.customize.DividerWithoutLastItemDecorator

/**
 * Created by Nhat Vo on 14/06/2021.
 */


fun RecyclerView.addDivider(padding: Int = 0) {
    val dividerDrawable = ContextCompat.getDrawable(context, R.drawable.divider)
    dividerDrawable?.let {
        this.addItemDecoration(
            DividerWithoutLastItemDecorator(it, padding.convertDpToPx())
        )
    }
}