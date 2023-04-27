package com.example.ulla_app.classes

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.util.Log
import android.view.View
import java.lang.Integer.min


class Joystick constructor(context: Context) : View(context) {

    private fun getScreenWidth(): Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }

    private fun getScreenHeight(): Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }

    //private val myCanvas = Canvas();
    private val colors = Paint();

    private var widthh = getScreenWidth();
    private var heightt = getScreenHeight();

    private var centerX = (widthh?.div(2))!!.toFloat();
    private var centerY = (heightt?.div(2))!!.toFloat();
    private var baseRadius = (min(widthh!!, heightt!!) / 3).toFloat();
    private var hatRadius = (min(widthh!!, heightt!!) / 5).toFloat();

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
        colors.setARGB(255, 50, 50, 50)
        canvas.drawCircle(centerX!!, centerY!!, baseRadius!!, colors)
        colors.setARGB(255, 0, 0, 255)
        canvas.drawCircle(centerX!!, centerY!!, hatRadius!!, colors)
    }
}