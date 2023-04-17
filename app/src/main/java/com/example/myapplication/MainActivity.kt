package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket

class MainActivity : AppCompatActivity() {
    private lateinit var webSocket: WebSocket
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val client = OkHttpClient()
        val request = Request.Builder()
            .url("ws://echo.websocket.org")
            .build()

        val listener = MyWebSocketListener()
        webSocket = client.newWebSocket(request, listener)

        webSocket.send("Test message") // should send via button(user intertracion from view) , not in main, just for test

        Handler(Looper.getMainLooper()).postDelayed({
            val statusCode = 1000 // Normal closure status code
            val reason = "Closing the connection"
            webSocket.close(statusCode, reason)
        }, 5000) // Delay of 5 seconds

    }
}