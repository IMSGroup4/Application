package com.example.ulla_app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ulla_app.R
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.ulla_app.classes.MowerPathView
import com.example.ulla_app.dataclasses.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class MapFragment : Fragment() {

    var mowerPathView: MowerPathView? = null
    val TAG = "MapFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mowerPathView = view?.findViewById<MowerPathView>(R.id.mowerPathView)

        val url = "https://ims-group4-backend.azurewebsites.net/api/positions"

        viewLifecycleOwner.lifecycleScope.launch {
            val response = makeApiCall(url)

            if (!response.isSuccessful) {
                Log.e(TAG, "Error: ${response.code}")
            }

            val responseBodyStr = response.body?.string()
            Log.d(TAG, "API Response: $responseBodyStr")

            val positions: List<Position> = Json.decodeFromString(ListSerializer(Position.serializer()), responseBodyStr ?: "[]")
            Log.d(TAG, "positions: $positions")

            for(position in positions){
                updateMowerPosition(position.x.toFloat(), position.y.toFloat())
            }

            //dummy data
            addLidarPoint(210F, 100F)
            addLidarPoint(240F, 130F)
            addLidarPoint(260F, 150F)
        }
    }

    private fun updateMowerPosition(x: Float, y: Float) {
        mowerPathView?.updateMowerPosition(x, y)
    }

    private fun addLidarPoint(x: Float, y: Float) {
        mowerPathView?.addLidarPoint(x, y)
    }

    private fun clear() {
        mowerPathView?.clear()
    }

    private suspend fun makeApiCall(url: String): Response {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url(url)
            .build()

        return withContext(Dispatchers.IO) {
            client.newCall(request).execute()
        }
    }


}