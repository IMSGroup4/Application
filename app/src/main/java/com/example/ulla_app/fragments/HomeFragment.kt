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
import androidx.core.view.isGone
import androidx.lifecycle.lifecycleScope
import com.example.ulla_app.HomeActivity
import com.example.ulla_app.R
import com.example.ulla_app.classes.*
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import org.json.JSONObject

class HomeFragment : Fragment() {

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
        val homeActivity = activity as HomeActivity

        connectButton?.setOnClickListener {

            //TODO: Start loading here
            val connected = myWebSocket.connect()
            if(connected){
                connectButton.visibility = View.GONE
                disconnectButton.visibility = View.VISIBLE
            }
            //TODO: Stop loading here

            // change disconnected textview to connected
            homeActivity.updateConnectionStatus(true)
        }

        disconnectButton?.setOnClickListener {
            val disconnected = myWebSocket.disconnect()

            if(disconnected){
                connectButton.visibility = View.VISIBLE
                disconnectButton.visibility = View.GONE
            }
            // change connected textview to disconnected
            homeActivity.updateConnectionStatus(false)
        }

        myWebSocket.messageListener { message : String ->
            activity?.runOnUiThread {
                output.text = message
            }
        }
    }

}