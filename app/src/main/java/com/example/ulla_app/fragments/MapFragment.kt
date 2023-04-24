package com.example.ulla_app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ulla_app.R
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import com.example.ulla_app.classes.MowerPathView

class MapFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mowerPathView = view.findViewById<MowerPathView>(R.id.mowerPathView)


        

    }
    fun updateRobotPosition(x: Float, y: Float) {
        mowerPathView.updateRobotPosition(x, y)
    }

    fun addLidarPoint(x: Float, y: Float) {
        mowerPathView.addLidarPoint(x, y)
    }

    fun clear() {
        mowerPathView.clear()
    }

}