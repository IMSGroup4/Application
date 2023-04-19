package com.example.ulla_app.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ulla_app.HomeActivity
import com.example.ulla_app.R
import com.example.ulla_app.classes.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val connectButton = view.findViewById<Button>(R.id.connect_button)
        val disconnectButton = view.findViewById<Button>(R.id.disconnect_button)
        val output = view.findViewById<TextView>(R.id.output)
        output.text = "HELLOOOOOOOO :)"
        connectButton?.setOnClickListener {
            myWebSocket.connect()
        }
        disconnectButton?.setOnClickListener {
            myWebSocket.disconnect()
        }

        myWebSocket.messageListener { message ->
            activity?.runOnUiThread {
                output.text = message
            }
        }
    }
}