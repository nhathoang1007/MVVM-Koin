package com.example.observableresearch.customize.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.marginBottom
import androidx.core.view.marginTop
import com.example.observableresearch.R
import com.example.observableresearch.extensions.dp
import com.example.observableresearch.extensions.getColor


@SuppressLint("Recycle")
class SpacingLinearLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

    var mSpacing: Int = 0

    init {
        kotlin.runCatching {
            context.obtainStyledAttributes(attrs, R.styleable.SpacingLinearLayout).apply {
                mSpacing = getDimension(R.styleable.SpacingLinearLayout_ll_spacing, 0.0f).toInt()
                Log.e("SpacingLinearLayout", "$mSpacing")
                this.recycle()
            }
        }
    }

    override fun onViewAdded(child: View?) {
        if (childCount > 1 && child != getChildAt(0)) {
            (child?.layoutParams as? LayoutParams)?.apply {
                when (orientation) {
                    HORIZONTAL -> {
                        setMargins(mSpacing, marginTop, marginEnd, marginBottom)
                    }
                    else -> {
                        setMargins(marginStart, mSpacing, marginEnd, marginBottom)
                    }
                }
            }
        }
        super.onViewAdded(child)
    }
}