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
import android.widget.FrameLayout
import android.widget.Switch
import androidx.core.content.ContextCompat
import com.example.ulla_app.R
import org.json.JSONObject
import java.lang.Integer.min
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

class Joystick @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private fun getScreenWidth(): Int {
        return context.resources.displayMetrics.widthPixels
    }

    private fun getScreenHeight(): Int {
        return context.resources.displayMetrics.heightPixels
    }

    private val joystickView = JoystickView(context)
    private val switchView = Switch(context)

    init {
        val bg = ContextCompat.getColor(context, R.color.bg)

        // Add the joystick view and switch view to the layout
        addView(joystickView)
        addView(switchView)

        // Set the switch to be at the top of the layout
        switchView.layoutParams =
            LayoutParams(400, 75).apply {
                gravity = android.view.Gravity.TOP or android.view.Gravity.CENTER_HORIZONTAL
                topMargin = 32
            }

        // Set the initial state of the switch to Joystick mode
        switchView.text = "  Joystick"
        switchView.setTextColor(Color.WHITE)
        switchView.setBackgroundColor(Color.rgb(0,31,92))
        switchView.isChecked = false

        // Set an OnCheckedChangeListener to switch between autonomous and joystick mode
        switchView.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                switchView.text = "  Autonomous"
                switchView.setTextColor(Color.BLACK)
                switchView.setBackgroundColor(bg)
                joystickView.visibility = View.INVISIBLE
                // Send a message to the server to switch to autonomous mode
                val timestamp = System.currentTimeMillis()
                myWebSocket.send(JSONObject().apply {
                    put("action", "autonomous")
                    put("x", null)
                    put("y", null)
                    put("timestamp", timestamp)
                })
            } else {
                switchView.text = "  Joystick"
                switchView.setTextColor(Color.WHITE)
                switchView.setBackgroundColor(Color.rgb(0,31,92))
                joystickView.visibility = View.VISIBLE
                // Send a message to the server to switch to joystick mode
                val timestamp = System.currentTimeMillis()
                myWebSocket.send(JSONObject().apply {
                    put("action", "joystick")
                    put("x", 0)
                    put("y", 0)
                    put("timestamp", timestamp)
                })
            }
        }
    }

    inner class JoystickView(context: Context) : View(context) {

        private val colors = Paint()

        private var widthh = getScreenWidth();
        private var heightt = getScreenHeight();

        private var centerX = (widthh?.div(2))!!.toFloat();
        private var centerY = (heightt?.div(3))!!.toFloat();
        private var baseRadius = (min(widthh!!, heightt!!) / 3).toFloat();
        private var hatRadius = (min(widthh!!, heightt!!) / 5).toFloat();

        private var hatX = centerX
        private var hatY = centerY

        private var lastX = 0.0
        private var lastY = 0.0
        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
            colors.setARGB(255, 50, 50, 50)
            canvas.drawCircle(centerX!!, centerY!!, baseRadius!!, colors)
            colors.setARGB(255, 0, 31, 92)
            canvas.drawCircle(hatX, hatY, hatRadius, colors)
        }

        override fun onTouchEvent(event: MotionEvent): Boolean {

            fun sendJoystickCoords(x: Double, y: Double) {
                val timestamp = System.currentTimeMillis()

                // only send joystick coordinates if there is a difference of greater than 0.2 for either x or y
                if (abs(x - lastX) > 0.2 || abs(y - lastY) > 0.2) {
                    lastX = x
                    lastY = y

                    val joystickCoords = JSONObject().apply {
                        put("action", "joystick")
                        put("x", lastX)
                        put("y", lastY)
                        put("timestamp", timestamp)
                    }

                    myWebSocket.send(joystickCoords)
                }
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
                sendJoystickCoords(0.0, 0.0)
                return true
            }
            // Indicate that the touch event was not handled
            return false
        }
    }
}
