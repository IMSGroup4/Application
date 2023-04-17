package com.example.ulla_app.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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
    private var client: OkHttpClient = OkHttpClient()
    private var ws: WebSocket? = null

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

        //client = OkHttpClient()
        val start = view.findViewById<Button>(R.id.start)
        //val output = view.findViewById<Button>(R.id.output)
        start?.setOnClickListener { start() }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun start() {
        val request = Request.Builder()
            .url("wss://ims-group4-backend.azurewebsites.net/ws/app")
            .build()

        val listener = MyWebSocketListener()
        ws = client.newWebSocket(request, listener)

        ws!!.send("Test message") // should send via button(user intertracion from view) , not in main, just for test

        Handler(Looper.getMainLooper()).postDelayed({ // Close socket after 5 seconds
            val statusCode = 1000 // Normal closure status code
            val reason = "Closing the connection"
            ws!!.close(statusCode, reason)
        }, 50000) // Delay of 5 seconds
    }

    /*private fun output(txt: String) {
        runOnUiThread {
            output?.text = "${output?.text}\n\n$txt"
        }
    }*/
}