package com.example.ulla_app

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.ulla_app.classes.*
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        this.supportActionBar?.hide()
        setContentView(R.layout.main_activity)

        val getStartedBtn = findViewById<Button>(R.id.get_started_btn)
        getStartedBtn.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}

