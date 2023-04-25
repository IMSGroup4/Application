package com.example.ulla_app

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ObjectsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
        setContentView(R.layout.objects_activity)

        val home_button = findViewById<Button>(R.id.home_button)
        val map_button = findViewById<Button>(R.id.map_button)
        val control_button = findViewById<Button>(R.id.control_button)
        val title = findViewById<TextView>(R.id.object_title)
        val image = findViewById<ImageView>(R.id.object_image)
        val x_coor = findViewById<TextView>(R.id.x_coordinate)
        val y_coor = findViewById<TextView>(R.id.y_coordinate)
        val delete_btn = findViewById<Button>( R.id.delete_btn)
        val obstacleList = ObstacleList()
    }
}