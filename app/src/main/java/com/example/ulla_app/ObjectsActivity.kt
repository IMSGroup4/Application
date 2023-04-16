package com.example.ulla_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ObjectsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
        setContentView(R.layout.objects_activity)
    }
}