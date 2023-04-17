package com.example.ulla_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
        setContentView(R.layout.main_activity)

        val getStartedBtn = findViewById<Button>(R.id.get_started_btn)
        getStartedBtn.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}

