package com.example.ulla_app.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

suspend fun makeApiGetCall(url: String): Response {
    val client = OkHttpClient()

    val request = Request.Builder()
        .url(url)
        .build()

    return withContext(Dispatchers.IO) {
        client.newCall(request).execute()
    }
}