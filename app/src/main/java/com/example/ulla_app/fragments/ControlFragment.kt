package com.example.ulla_app.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ulla_app.R
import com.example.ulla_app.classes.Joystick
import kotlinx.coroutines.joinAll


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
/**
 * A simple [Fragment] subclass.
 * Use the [ControlFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ControlFragment : Fragment() {

    // TODO: Rename and change types of parameters
    //var joystickView: Joystick? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        Log.d("debug", "onCreateView before")
        var test = inflater.inflate(R.layout.fragment_control, container, false)
        if (test != null) {
            Log.d("debug", "not null")
        }
        else {
            Log.d("debug", "null")
        }

        return inflater.inflate(R.layout.fragment_control, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        Log.d("debug", "onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        val joystickView = view.findViewById<Joystick>(R.id.joystickView)
        view.invalidate()
    }
}