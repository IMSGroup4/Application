package com.example.ulla_app.classes

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.ulla_app.R

class MowerPathView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    private var showMowerPathView = true

    private val mowerPath = Path()
    private val obstacleCoordinatesPoints = mutableListOf<Pair<Float, Float>>()
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

    private val mowerBitmap: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.small_ulla_160x160)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = w / 2f
        centerY = h / 2f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.save()
        canvas.translate(centerX, centerY)


        canvas.drawBitmap(
            mowerBitmap,
            lastMowerPoint.first - mowerBitmap.width / 2,
            lastMowerPoint.second - mowerBitmap.height / 2,
            null
        )
        canvas.drawPath(mowerPath, mowerPathPaint)

        for (point in obstacleCoordinatesPoints) {
            canvas.drawCircle(point.first, point.second, 20f, lidarPointPaint)
        }




        canvas.restore()
    }

    fun updateMowerPosition(x: Float, y: Float) {
        mowerPath.lineTo(x, y)
        lastMowerPoint = Pair(x,y)
        invalidate()
    }

    fun addObstacleCoordinatePoint(x: Float, y: Float) {
        obstacleCoordinatesPoints.add(Pair(x, y))
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
        obstacleCoordinatesPoints.clear()
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