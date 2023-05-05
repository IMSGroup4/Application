package com.example.ulla_app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ulla_app.R
import com.example.ulla_app.classes.ObstacleList
import com.example.ulla_app.classes.RecyclerViewAdapter
import com.example.ulla_app.dataclasses.DummyData
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ObstaclesFragment : Fragment() {
    private lateinit var deleteBtn: FloatingActionButton

    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var recyclerView: RecyclerView
    private val obstacleList = ObstacleList()

    lateinit var title: Array<String>
    lateinit var x: ArrayList<Int>
    lateinit var y: ArrayList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_obstacles, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObstacleList()
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.recycle_view)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = RecyclerViewAdapter(obstacleList)
        recyclerView.adapter = adapter

    }


    private fun initObstacleList() {

    }

}

