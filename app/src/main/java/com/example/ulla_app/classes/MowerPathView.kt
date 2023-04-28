package com.example.ulla_app.classes

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class MowerPathView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    private var showMowerPathView = true

    private val mowerPath = Path()
    private val lidarPoints = mutableListOf<Pair<Float, Float>>()
    private var lastMowerPoint = Pair<Float, Float>(0F, 0F)
    private val mowerPathPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
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

    private val mowerPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.GREEN
        style = Paint.Style.FILL
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = w / 2f
        centerY = h / 2f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.save()
        canvas.translate(centerX, centerY)

        if(showMowerPathView){
            canvas.drawCircle(lastMowerPoint.first, lastMowerPoint.second, 30f, mowerPaint)
            canvas.drawPath(mowerPath, mowerPathPaint)
        } else {
            canvas.drawCircle(0F, 0F, 30f, mowerPaint)
            for (point in lidarPoints) {
                canvas.drawCircle(point.first, point.second, 20f, lidarPointPaint)
            }
        }



        canvas.restore()
    }

    fun updateMowerPosition(x: Float, y: Float) {
        mowerPath.lineTo(x, y)
        lastMowerPoint = Pair(x,y)
        invalidate()
    }

    fun addLidarPoint(x: Float, y: Float) {
        lidarPoints.add(Pair(x, y))
        invalidate()
    }

    fun switchToSurroundings(){
        showMowerPathView = false
        invalidate()
    }

    fun switchToMowerPath(){
        showMowerPathView = true
        invalidate()
    }

    fun clear() {
        mowerPath.reset()
        lidarPoints.clear()
        invalidate()
    }

    fun updateMowerPoint(x: Float, y: Float){
        lastMowerPoint = Pair(x, y)
        invalidate()
    }

    fun getShowMowerPathView(): Boolean{
        return showMowerPathView
    }
}