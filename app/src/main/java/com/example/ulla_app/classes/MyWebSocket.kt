package com.example.ulla_app.classes

import android.os.Handler
import android.os.Looper
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import org.json.JSONObject
import org.json.JSONStringer

val myWebSocket = MyWebSocket()

class MyWebSocket {

    private val TAG = "MyWebSocket"

    private var ws: WebSocket? = null
    private val wsListener = MyWebSocketListener()
    private val client: OkHttpClient = OkHttpClient()

    fun connect(): Boolean {
        Log.d(TAG, "connect() called")
        val request = Request.Builder()
            .url("wss://ims-group4-backend.azurewebsites.net/ws/app")
            .build()

        ws = client.newWebSocket(request, wsListener)

        return ws != null
    }

    fun disconnect(): Boolean {
        if(ws != null){
            val statusCode = 1000 // Normal closure status code
            val reason = "App disconnected manually"
            ws!!.close(statusCode, reason)
            Log.d(TAG, "disconnect() called")
            return true
        } else {
            return false
        }
    }

    fun send(jsonObject: JSONObject){ // TODO: Maybe we should just have for example "action" and "direction" here as arguments, and apply them to a JSONObject in the function?
        if(ws != null){
            val jsonString = jsonObject.toString()
            ws!!.send(jsonString)
            Log.d(TAG, "Sent: $jsonString")
        }
    }

    fun messageListener(callback: (message: String) -> Unit){
        wsListener.setOnMessageCallback(callback)
        Log.d(TAG, "messageListener() called")
    }


}