package com.example.ulla_app.classes

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.ulla_app.dataclasses.ObstaclePosition
import android.util.Base64
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class ObstacleList {
    private var obstacleList = listOf<ObstaclePosition>()

    fun getObstacle(index: Int): ObstaclePosition{
        return obstacleList[index]
    }
    fun getObstacleList(): List<ObstaclePosition>{
        return obstacleList
    }
    fun getObstacleImage(obstacleIndex: Int): Bitmap {
        val imageBytes = Base64.decode(obstacleList[obstacleIndex].base64_image, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }
    fun getObstacleClassification(obstacleIndex: Int): Pair<String,Float>{
        return Pair<String, Float>(obstacleList[obstacleIndex].infos_image[0].description, obstacleList!![obstacleIndex].infos_image[0].score)
    }
    val size: Int
        get() = obstacleList.size


    fun populateObstacleList(obstacleList: List<ObstaclePosition>){
        this.obstacleList = obstacleList
    }


}