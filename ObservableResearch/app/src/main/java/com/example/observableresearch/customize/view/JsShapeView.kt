package com.example.observableresearch.customize.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.observableresearch.R
import com.example.observableresearch.extensions.dp
import com.example.observableresearch.utils.EnumCompanion


@SuppressLint("Recycle")
class JsShapeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attrs, defStyle) {

    var mSize: Int = 0
    var mShape: Shape = Shape.RECT
    var mShapeRadius: Int = 16.dp
    var mFillColor: Int = Color.GRAY
    var mStrokeColor: Int? = null
    var mStrokeWidth: Int = 0.dp
    var mShadowColor: Int? = null
    var mShadowPosition: ShadowPosition = ShadowPosition.BOTTOM

    init {
        kotlin.runCatching {
            context.obtainStyledAttributes(attrs, R.styleable.JsShapeView).apply {
                mShape = Shape.getByValue(getInt(R.styleable.JsShapeView_js_shape, 0), Shape.RECT)
                mFillColor = getColor(R.styleable.JsShapeView_js_fill_color, Color.WHITE)
                if (hasValue(R.styleable.JsShapeView_js_stroke_color)) {
                    mStrokeColor = getColor(R.styleable.JsShapeView_js_stroke_color, Color.WHITE)
                }
                mShapeRadius = getDimensionPixelSize(R.styleable.JsShapeView_js_shape_radius, 16.dp)
                mStrokeWidth = getDimensionPixelSize(R.styleable.JsShapeView_js_stroke_width, 0.dp)

                if (hasValue(R.styleable.JsShapeView_js_shadow_color)) {
                    mShadowColor = getColor(R.styleable.JsShapeView_js_shadow_color, Color.WHITE)
                }
                mShadowPosition = ShadowPosition.getByValue(
                    getInt(R.styleable.JsShapeView_js_shadow_gravity, 0),
                    ShadowPosition.BOTTOM
                )

                this.recycle()
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mSize = measuredWidth.coerceAtMost(measuredHeight)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val paintFill = Paint().apply {
            isAntiAlias = true
            color = mFillColor
            if (mShadowColor != null) {
                setShadowLayer(
                    16.dp.toFloat(),
                    mShadowPosition.x,
                    mShadowPosition.y,
                    mShadowColor!!
                )
            }
        }
        val paintStroke = Paint().apply {
            isAntiAlias = true
            color = mStrokeColor ?: Color.TRANSPARENT
            strokeWidth = mStrokeWidth.toFloat()
            style = Paint.Style.STROKE
        }
        val margin = if (mShadowColor != null) {
            16
        } else {
            0
        }.dp.toFloat()
        when (mShape) {
            Shape.CIRCLE -> {
                val radius = (mSize / 2).toFloat() - margin
                canvas?.drawCircle(width.toFloat() / 2, height.toFloat() / 2, radius, paintFill)
                canvas?.drawCircle(width.toFloat() / 2, height.toFloat() / 2, radius, paintStroke)
            }
            Shape.RECT, Shape.SQUARE -> {
                val rect = if (mShape == Shape.RECT) {
                    RectF(margin, margin, width - margin, height - margin)
                } else {
                    val centerOfCanvas = Point(width / 2, height / 2)
                    RectF(
                        centerOfCanvas.x - (mSize / 2 - margin),
                        centerOfCanvas.y - (mSize / 2 - margin),
                        centerOfCanvas.x + (mSize / 2 - margin),
                        centerOfCanvas.y + (mSize / 2 - margin)
                    )
                }
                canvas?.drawRoundRect(
                    rect,
                    mShapeRadius.toFloat(),
                    mShapeRadius.toFloat(),
                    paintFill
                )
                canvas?.drawRoundRect(
                    rect,
                    mShapeRadius.toFloat(),
                    mShapeRadius.toFloat(),
                    paintStroke
                )
            }
        }
    }

    enum class Shape(val style: Int) {
        CIRCLE(0),
        RECT(1),
        SQUARE(2);

        companion object : EnumCompanion<Int, Shape>(values().associateBy(Shape::style))
    }

    enum class ShadowPosition(val gravity: Int, val x: Float, val y: Float) {
        TOP(0, -4f, -4f),
        START(1, -4f, 0f),
        END(2, 4f, 0f),
        BOTTOM(3, 4f, 4f);

        companion object : EnumCompanion<Int, ShadowPosition>(
            values().associateBy(ShadowPosition::gravity)
        )
    }
}