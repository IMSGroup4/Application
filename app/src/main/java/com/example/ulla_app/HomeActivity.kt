package com.example.ulla_app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ulla_app.R
import okhttp3.*
import okio.ByteString
import okio.ByteString.Companion.decodeHex
import androidx.fragment.app.Fragment
import com.example.ulla_app.classes.myWebSocket
import com.example.ulla_app.fragments.*


class HomeActivity : AppCompatActivity() {
    private var start: Button? = null
    private var output: TextView? = null 
    var client: OkHttpClient? = null
    lateinit var webSocket: WebSocket

    private val mapFragment = MapFragment()
    private val controlFragment = ControlFragment()
    private val homeFragment = HomeFragment()
    private val obstaclesFragment = ObstaclesFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
        setContentView(R.layout.home_activity)
        val obstaclesButton = findViewById<Button>(R.id.obstacles_button)
        val mapButton = findViewById<Button>(R.id.map_button)
        val controlButton = findViewById<Button>(R.id.control_button)
        val homeButton = findViewById<Button>(R.id.home_button)

        replaceFragment(homeFragment)

        mapButton.setOnClickListener {
            replaceFragment(mapFragment)
        }

        controlButton.setOnClickListener {
            replaceFragment(controlFragment)
        }

        obstaclesButton.setOnClickListener {
            replaceFragment(obstaclesFragment)
        }

        homeButton.setOnClickListener {
            replaceFragment(homeFragment)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

    fun updateConnectionStatus(isConnected: Boolean) {
        val connectionStatusTextView = findViewById<TextView>(R.id.connectionStatus)

        if (isConnected) {
            connectionStatusTextView.text = getString(R.string.connected)
            connectionStatusTextView.setBackgroundResource(R.color.success)
        } else {
            connectionStatusTextView.text = getString(R.string.disconnected)
            connectionStatusTextView.setBackgroundResource(R.color.failure)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        myWebSocket.disconnect()
    }
}
