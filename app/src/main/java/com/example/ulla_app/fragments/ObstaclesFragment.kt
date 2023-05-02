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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ObstaclesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ObstaclesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var deleteBtn: FloatingActionButton
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var dummyDataArrayList: ArrayList<DummyData>  //List of objects
    private val obstacleList = ObstacleList()

    lateinit var title: Array<String>
    lateinit var x: ArrayList<Int>
    lateinit var y: ArrayList<Int>





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
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
        initDummyData()
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.recycle_view)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = RecyclerViewAdapter(obstacleList)
        recyclerView.adapter = adapter

    }


    private fun initDummyData() { // ändra så den anropar ObtsaclePosition
        dummyDataArrayList = arrayListOf<DummyData>()

        title = arrayOf(
            "Cow",
            "Dog",
            "Child",
            "Vase"
        )

        x = arrayListOf(12, 41, 21, 23)
        y = arrayListOf(11, 44, 26, 13)

        for (i in title.indices) {
            val data = DummyData(title[i], x[i], y[i])
            dummyDataArrayList.add(data)
        }
    }




    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ObstaclesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ObstaclesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}

