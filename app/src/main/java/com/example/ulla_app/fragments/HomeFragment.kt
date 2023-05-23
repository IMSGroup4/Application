package com.example.ulla_app.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.lifecycle.lifecycleScope
import com.example.ulla_app.HomeActivity
import com.example.ulla_app.R
import com.example.ulla_app.api.makeApiGetCall
import com.example.ulla_app.classes.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import org.json.JSONObject

class HomeFragment : Fragment() {
    val TAG = "HomeFragment"

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
        val homeActivity = activity as HomeActivity
        val newSessionButton = view.findViewById<Button>(R.id.new_session_button)

        if(WS_IS_CONNECTED){
            connectButton.visibility = View.GONE
            disconnectButton.visibility = View.VISIBLE
        } else {
            connectButton.visibility = View.VISIBLE
            disconnectButton.visibility = View.GONE
        }

        connectButton?.setOnClickListener {

            //TODO: Start loading here
            val connected = myWebSocket.connect()
            if(connected){
                connectButton.visibility = View.GONE
                disconnectButton.visibility = View.VISIBLE
            }
            //TODO: Stop loading here

            // change disconnected textview to connected
            homeActivity.updateConnectionStatus()
        }

        disconnectButton?.setOnClickListener {
            val disconnected = myWebSocket.disconnect()

            if(disconnected){
                connectButton.visibility = View.VISIBLE
                disconnectButton.visibility = View.GONE
            }
            // change connected textview to disconnected
            homeActivity.updateConnectionStatus()
        }

        newSessionButton?.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                val response = makeApiGetCall("https://ims-group-4-backend-david.azurewebsites.net/api/new_session")

                if(!response.isSuccessful){
                    Log.e(TAG, "Error: ${response.code}")
                    Toast.makeText(requireContext(), "Error: Were not able to start new session...", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(requireContext(), "New session started!", Toast.LENGTH_SHORT).show()
                }

            }
        }

    }

}