package com.example.ulla_app.classes

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.ulla_app.dataclasses.ObstaclePosition
import android.util.Base64

class ObstacleList {
    private val obstacleList = mutableListOf<ObstaclePosition>()

    fun addObstacle(obstacle :ObstaclePosition){
        obstacleList.add(obstacle)
    }
    fun removeObstacleAtIndex(index : Int){
        obstacleList.removeAt(index)
    }
    fun getObstacle(index : Int) : ObstaclePosition{
        return obstacleList[index]
    }
    fun getObstacleList() : MutableList<ObstaclePosition>{
        return obstacleList
    }
    fun getObstacleImage(obstacleIndex: Int): Bitmap {
        val imageBytes = Base64.decode(obstacleList[obstacleIndex].photoStringB64, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }
    fun getObstacleClassification(obstacleIndex : Int) : Pair<String,Float>{
        return Pair<String, Float>(obstacleList[obstacleIndex].photoInformation.description, obstacleList[obstacleIndex].photoInformation.score)
    }

}