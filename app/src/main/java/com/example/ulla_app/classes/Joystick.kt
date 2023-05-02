package com.example.ulla_app.classes

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import org.json.JSONObject
import java.lang.Integer.min
import java.lang.Math.sqrt
import kotlin.math.*


class Joystick @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private fun getScreenWidth(): Int {
        return context.resources.displayMetrics.widthPixels
    }

    private fun getScreenHeight(): Int {
        return context.resources.displayMetrics.heightPixels
    }

    //private val myCanvas = Canvas();
    private val colors = Paint();

    private var widthh = getScreenWidth();
    private var heightt = getScreenHeight();

    private var centerX = (widthh?.div(2))!!.toFloat();
    private var centerY = (heightt?.div(3))!!.toFloat();
    private var baseRadius = (min(widthh!!, heightt!!) / 3).toFloat();
    private var hatRadius = (min(widthh!!, heightt!!) / 5).toFloat();

    private var hatX = centerX
    private var hatY = centerY

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
        colors.setARGB(255, 50, 50, 50)
        canvas.drawCircle(centerX!!, centerY!!, baseRadius!!, colors)
        colors.setARGB(255, 0, 0, 255)
        canvas.drawCircle(hatX, hatY, hatRadius, colors)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        fun sendJoystickCoords(x: Double, y: Double) {
            val timestamp = System.currentTimeMillis()
            val joystickCoords = JSONObject().apply {
                put("action", "joystick")
                put("x", x)
                put("y", y)
                put("timestamp", timestamp)
            }
            Log.d("debug", "x : " + x.toString());
            Log.d("debug", "y : " + y.toString());
            myWebSocket.send(joystickCoords)
        }

        val action = event.action
        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE) {
            // Update the position of the joystick based on the touch coordinates
            val x = event.x
            val y = event.y
            val dx = x - centerX
            val dy = y - centerY
            if (dx * dx + dy * dy <= baseRadius * baseRadius) {
                hatX = x
                hatY = y
            } else {
                val angle = atan2(dy.toDouble(), dx.toDouble())
                hatX = centerX + (baseRadius * cos(angle)).toFloat()
                hatY = centerY + (baseRadius * sin(angle)).toFloat()
            }
            // Redraw the joystick
            invalidate()
            // Send the joystick coordinates
            sendJoystickCoords((((hatX - centerX) / baseRadius) * 100.0).roundToInt() / 100.0, (((centerY - hatY) / baseRadius) * 100.0).roundToInt() / 100.0)
            return true
        } else if (action == MotionEvent.ACTION_UP) {
            // Reset the position of the joystick to the center
            hatX = centerX
            hatY = centerY
            // Redraw the joystick
            invalidate()
            // Send the joystick coordinates
            sendJoystickCoords(0.0,0.0)
            return true
        }
        return super.onTouchEvent(event)
    }

}