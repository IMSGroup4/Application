package com.example.ulla_app.classes

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ulla_app.R
import com.example.ulla_app.dataclasses.DummyData
import com.example.ulla_app.dataclasses.ObstaclePosition
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.coroutines.CoroutineStart
import kotlin.io.encoding.Base64

//val obstacleList : ObstacleList()
class RecyclerViewAdapter(private val obstacleList: ObstacleList) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    inner class ViewHolder(viewItem : View): RecyclerView.ViewHolder(viewItem){
        val title: TextView = viewItem.findViewById(R.id.obstacle_title)
        val x: TextView = viewItem.findViewById(R.id.x_coordinate)
        val y: TextView = viewItem.findViewById(R.id.y_coordinate)


    } // deklarera variablerna frånObstaclePosition

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewItem = LayoutInflater.from(parent.context).inflate(R.layout.obstacle_item, parent, false)
        return ViewHolder(viewItem)
    }

    override fun getItemCount(): Int {
        return obstacleList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val obstacle = obstacleList.getObstacle(position)
        holder.title.text = obstacle.photoInformation.description
        holder.x.text = obstacle.x.toString()
        holder.y.text = obstacle.y.toString()
        val image = obstacleList.getObstacleImage(position)


    }

}
