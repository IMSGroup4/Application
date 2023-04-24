package com.example.ulla_app.classes

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class MowerPathView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    private val robotPath = Path()
    private val lidarPoints = mutableListOf<Pair<Float, Float>>()
    private val robotPathPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLUE
        strokeWidth = 5f
        style = Paint.Style.STROKE
    }
    private val lidarPointPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
        style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(robotPath, robotPathPaint)
        for (point in lidarPoints) {
            canvas.drawCircle(point.first, point.second, 3f, lidarPointPaint)
        }
    }
}