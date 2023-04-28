package com.example.ulla_app.classes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ulla_app.R
import com.example.ulla_app.dataclasses.DummyData


class RecyclerViewAdapter(private val dummyData: ArrayList<DummyData>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(viewItem : View): RecyclerView.ViewHolder(viewItem){
        val title: TextView = viewItem.findViewById(R.id.obstacle_title)
        val x: TextView = viewItem.findViewById(R.id.x_coordinate)
        val y: TextView = viewItem.findViewById(R.id.y_coordinate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewItem = LayoutInflater.from(parent.context).inflate(R.layout.obstacle_item, parent, false)
        return ViewHolder(viewItem)
    }

    override fun getItemCount(): Int {
        return dummyData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = dummyData[position]
        holder.title.text = currentItem.title
        holder.x.text = currentItem.x.toString()
        holder.y.text = currentItem.y.toString()

    }

}
