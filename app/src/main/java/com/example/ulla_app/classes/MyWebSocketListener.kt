package com.example.ulla_app.classes

import android.util.Log
import okhttp3.EventListener
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

class MyWebSocketListener : WebSocketListener() {
    private val TAG = "MyWebSocketListener"
    private var onMessageCallback: ((message: String) -> Unit)? = null
    override fun onOpen(webSocket: WebSocket, response: Response) {
        // Connection opened
        Log.d(TAG, "Connection opened")
    }

    fun setOnMessageCallback(callback: (message: String) -> Unit){
        Log.d(TAG, "setOnMessageCallback() called")
        onMessageCallback = callback
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        // Received a text message
        Log.d(TAG, "Received message: $text")
        onMessageCallback?.invoke(text)
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        // Connection is about to close
        Log.d(TAG, "Connection is about to close")
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        // Connection closed
        Log.d(TAG, "Connection closed")
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        // Connection failed
        Log.e(TAG, "Connection failed")
    }

}

