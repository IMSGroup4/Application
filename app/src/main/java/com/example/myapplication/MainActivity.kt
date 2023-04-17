package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import okio.ByteString
import okio.ByteString.Companion.decodeHex


class MainActivity : AppCompatActivity() {
    private var start: Button? = null
    private var output: TextView? = null
    private var client: OkHttpClient? = null

    private inner class EchoWebSocketListener : WebSocketListener() {
        private val NORMAL_CLOSURE_STATUS = 1000

        override fun onOpen(webSocket: WebSocket, response: Response) {
            webSocket.send("Hello!")
            webSocket.send("What's up ?")
            // webSocket.send("deadbeef".decodeHex())
            webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye !")
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            output("Receiving : $text")
        }

        override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
            output("Receiving bytes : ${bytes.hex()}")
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            webSocket.close(NORMAL_CLOSURE_STATUS, null)
            output("Closing : $code / $reason")
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            output("Error : ${t.message}")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        start = findViewById(R.id.start)
        output = findViewById(R.id.output)
        client = OkHttpClient()

        start?.setOnClickListener { start() }
    }

    private fun start() {
        val request: Request = Request.Builder().url("wss://ws.postman-echo.com/raw").build()
        // val request: Request = Request.Builder().url("wss://ims-group4-backend.azurewebsites.net/ws/app").build()
        val listener: EchoWebSocketListener = EchoWebSocketListener()
        val ws: WebSocket = client!!.newWebSocket(request, listener)

        client?.dispatcher?.executorService?.shutdown()
    }

    private fun output(txt: String) {
        runOnUiThread {
            output?.text = "${output?.text}\n\n$txt"
        }
    }
}
