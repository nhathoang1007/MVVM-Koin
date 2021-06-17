package com.example.observableresearch.customize.view

import android.R.attr
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.example.observableresearch.extensions.dp


@SuppressLint("Recycle")
class CircleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attrs, defStyle) {

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val paintFill = Paint().apply {
            isAntiAlias = true
            color = Color.RED
            style = Paint.Style.FILL
        }
//        val paintStroke = Paint().apply {
//            isAntiAlias = true
//            color = Color.BLACK
//            strokeWidth = 3.dp.toFloat()
//            style = Paint.Style.STROKE
//        }
//        canvas?.drawCircle(width.toFloat()/2, height.toFloat()/2, 100f, paintFill)
//        canvas?.drawCircle(width.toFloat()/2, height.toFloat()/2, 100f, paintStroke)
        val rect = RectF(
            40f,
            40f,
            width.toFloat() - 20.dp.toFloat(),
            height.toFloat() - 20.dp.toFloat()
        )
        canvas?.drawRoundRect(rect, 16.dp.toFloat(), 16.dp.toFloat(), Paint().apply {
            color = Color.YELLOW
            setShadowLayer(16.dp.toFloat(), 10f, 10f, Color.BLACK);
        })
        canvas?.drawRoundRect(rect, 16.dp.toFloat(), 16.dp.toFloat(), Paint().apply {
            color = Color.GREEN
            strokeWidth = 2.dp.toFloat()
            style = Paint.Style.STROKE
        })
    }
}