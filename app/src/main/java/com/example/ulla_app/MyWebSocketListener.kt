package com.example.ulla_app

import android.util.Log
import okhttp3.Response
    import okhttp3.WebSocket
    import okhttp3.WebSocketListener
    import okio.ByteString

    class MyWebSocketListener : WebSocketListener() {
        private val TAG = "WebSocket"
        override fun onOpen(webSocket: WebSocket, response: Response) {
            // Connection opened
            Log.d(TAG, "Connection opened")
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            // Received a text message
            Log.d(TAG, "Received message: $text")
        }

        override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
            // Received a binary message
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            // Connection is about to close
        }

        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            // Connection closed
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            // Connection failed
        }
    }

