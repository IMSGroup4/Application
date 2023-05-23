package com.example.ulla_app.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ulla_app.R
import com.example.ulla_app.api.makeApiGetCall
import com.example.ulla_app.classes.ObstacleList
import com.example.ulla_app.classes.RecyclerViewAdapter
import com.example.ulla_app.dataclasses.ObstaclePosition
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class ObstaclesFragment : Fragment() {
    private lateinit var deleteBtn: FloatingActionButton

    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var recyclerView: RecyclerView
    private val obstacleList = ObstacleList()
    private val TAG = "ObstaclesFragment"

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

        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.recycle_view)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(false)
        adapter = RecyclerViewAdapter(obstacleList)
        recyclerView.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            initObstacleList()
        }

    }


    private suspend fun initObstacleList() {
        val obstacleUrl = "https://ims-group-4-backend-david.azurewebsites.net/api/obstacles/"

        var responseObstacles = makeApiGetCall(obstacleUrl)
        if (!responseObstacles.isSuccessful) {
            Log.e(TAG, "Error: ${responseObstacles.code}")
        } else {
            var responseObstacleBodyStr = responseObstacles.body?.string()
            Log.d(TAG, "API Response: $responseObstacleBodyStr")

            var obstacles: List<ObstaclePosition> = Json.decodeFromString(
                ListSerializer(ObstaclePosition.serializer()),
                responseObstacleBodyStr ?: "[]"
            )
            Log.d(TAG, "obstacles: $obstacles")

            obstacleList.populateObstacleList(obstacles)

            adapter.notifyDataSetChanged()
        }

    }

}

