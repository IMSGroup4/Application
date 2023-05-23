package com.example.ulla_app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ulla_app.R
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import com.example.ulla_app.api.makeApiGetCall
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
import java.util.Timer
import java.util.TimerTask

class MapFragment : Fragment() {

    var mowerPathView: MowerPathView? = null
    val TAG = "MapFragment"
    private var timer: Timer? = null
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

        val positionsUrl = "https://ims-group-4-backend-david.azurewebsites.net/api/positions"
        val surroundingsUrl = "https://ims-group-4-backend-david.azurewebsites.net/api/surrounding"

        val switchMapButton = view.findViewById<Button>(R.id.switch_map_button)
        switchMapButton?.setOnClickListener {
            Log.d(TAG, "isMowerPathViewShowing(): ${isMowerPathViewShowing()}")
            if (isMowerPathViewShowing()){ // i have given up on life :)
                switchToSurroundings()
                switchMapButton.setText(R.string.switch_to_surroundings)
            } else {
                switchToMowerPath()
                switchMapButton.setText(R.string.switch_to_mower_path)
            }
        }

        val timerTask = object : TimerTask() {
            override fun run() {
                viewLifecycleOwner.lifecycleScope.launch {
                    var responsePositions = makeApiGetCall(positionsUrl)
                    var responseBodyPositionsStr: String? = ""
                    var positions:List<Coordinate> = emptyList()
                    var lastMowerPosition = Coordinate(0,0)

                    if (!responsePositions.isSuccessful) {
                        Log.e(TAG, "Error: ${responsePositions.code}")
                    } else {
                        responseBodyPositionsStr = responsePositions.body?.string()
                        Log.d(TAG, "API Response: $responseBodyPositionsStr")

                        positions = Json.decodeFromString(
                        ListSerializer(Coordinate.serializer()),
                        responseBodyPositionsStr ?: "[]"
                        )
                        Log.d(TAG, "positions: $positions")

                        lastMowerPosition = positions.last()
                    }


                    var responseSurroundings = makeApiGetCall(surroundingsUrl)
                    var responseBodySurroundingsStr: String? = ""
                    var surroundings: List<Coordinate> = emptyList()

                    if (!responseSurroundings.isSuccessful) {
                        Log.e(TAG, "Error: ${responseSurroundings.code}")
                    } else {
                        responseBodySurroundingsStr = responseSurroundings.body?.string()
                        Log.d(TAG, "API Response: $responseBodySurroundingsStr")

                        surroundings= Json.decodeFromString(
                            ListSerializer(Coordinate.serializer()),
                            responseBodySurroundingsStr ?: "[]"
                        )
                        Log.d(TAG, "surroundings: $surroundings")
                    }


                    clear()

                    for (position in positions) {
                        updateMowerPosition(position.x.toFloat(), position.y.toFloat())
                    }

                    updateMowerPoint(lastMowerPosition.x.toFloat(), lastMowerPosition.y.toFloat())

                    for (surrounding in surroundings) {
                        addLidarPoint(surrounding.x.toFloat(), surrounding.y.toFloat())
                    }

                }
            }
        }

        timer = Timer()
        timer?.scheduleAtFixedRate(timerTask, 0, 5000)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        timer?.cancel()
        timer = null
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

    private fun updateMowerPoint(x: Float, y: Float) {
        mowerPathView?.updateMowerPoint(x, y)
    }

    private fun switchToMowerPath(){
        mowerPathView?.switchToMowerPath()
    }

    private fun switchToSurroundings(){
        mowerPathView?.switchToSurroundings()
    }

    private fun isMowerPathViewShowing(): Boolean {
        return mowerPathView!!.getShowMowerPathView()
    }


}