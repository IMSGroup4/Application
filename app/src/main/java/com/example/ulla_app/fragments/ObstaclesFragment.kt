package com.example.ulla_app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.ulla_app.R
import com.example.ulla_app.classes.ObstacleList
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
    private val obstacleList = ObstacleList()


    data class Obstacle(val title: String, val x: String, val y: String)
    val obstacles = listOf(
        Obstacle("Vase", "28.1215", "51.4512"),
        Obstacle("Cat", "32.1254","21.4511"),
        Obstacle("Cow", "25.1254","11.4515")
    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       println(obstacles)
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
        return inflater.inflate(R.layout.obstacle_item, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = view.findViewById<TextView>(R.id.obstacle_title)
        val x = view.findViewById<TextView>(R.id.x_coordinate)
        val y = view.findViewById<TextView>(R.id.y_coordinate)


        displayObstacle(title, x,y, obstacles)
    }

    private fun displayObstacle(title: TextView, x: TextView,y: TextView,obstacles: List<Obstacle>){
        obstacles.forEach { data->
            title.text = data.title
            x.text = data.x
            y.text = data.y
        }
    }


  /*  fun deleteObstacle(index: Int){
        obstacleList.removeObstacleAtIndex(index)
    }
*/

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

