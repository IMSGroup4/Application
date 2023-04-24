package com.example.ulla_app.classes

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class MowerPathView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    private val mowerPath = Path()
    private val lidarPoints = mutableListOf<Pair<Float, Float>>()
    private val robotPathPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLUE
        strokeWidth = 10f
        style = Paint.Style.STROKE
    }
    private val lidarPointPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
        style = Paint.Style.FILL
    }
    private var centerX = 0f
    private var centerY = 0f

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = w / 2f
        centerY = h / 2f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.save()
        canvas.translate(centerX, centerY)

        canvas.drawPath(mowerPath, robotPathPaint)
        for (point in lidarPoints) {
            canvas.drawCircle(point.first, point.second, 20f, lidarPointPaint)
        }

        canvas.restore()
    }

    fun updateMowerPosition(x: Float, y: Float) {
        mowerPath.lineTo(x, y)
        invalidate()
    }

    fun addLidarPoint(x: Float, y: Float) {
        lidarPoints.add(Pair(x, y))
        invalidate()
    }

    fun clear() {
        mowerPath.reset()
        lidarPoints.clear()
        invalidate()
    }
}