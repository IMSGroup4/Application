package com.example.ulla_app.fragments

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.ulla_app.R
import com.example.ulla_app.classes.ObstacleList

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
    private var param1: String? = null
    private var param2: String? = null
    private val obstacleList = ObstacleList()

    val testData =  listOf(
        mapOf(
            "x" to "51.4463",
            "y" to "6.6396",
            "title" to "vase",
            "img" to "@drawable/success.png"
        ),
        mapOf(

            "x" to "51.3311",
            "y" to "6.5616",
            "title" to "dog",
            "img" to "@drawable/error.png"
        )
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println(testData)
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

        val scannedObject = view.findViewById<LinearLayout>(R.id.scanned_object)
        val title = view.findViewById<TextView>(R.id.object_title)
        val image = view.findViewById<ImageView>(R.id.object_image)
        val x_coor = view.findViewById<TextView>(R.id.x_coordinate)
        val y_coor = view.findViewById<TextView>(R.id.y_coordinate)
        val delete_btn = view.findViewById<Button>( R.id.delete_btn)

        title.text = testData[0].get("title").toString()
        x_coor.text = testData[0].get("x").toString()
        y_coor.text = testData[0].get("y").toString()

        val draw = ContextCompat.getDrawable(requireContext(), R.drawable.success)
        image.setImageDrawable(draw)
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

