package com.example.ulla_app.classes

import com.example.ulla_app.dataclasses.ObstaclePosition

class ObstacleList {
    private val list = mutableListOf<List<ObstaclePosition>>()

    fun addObstacle(obstacle :List<ObstaclePosition>){
        list.add(obstacle)
    }
    fun removeObstacleAtIndex(index : Int){
        list.removeAt(index)
    }
    fun getObstacle(index : Int) : List<ObstaclePosition>{
        return list[index]
    }
    fun getObstacleList() : MutableList<List<ObstaclePosition>>{
        return list
    }
    fun getObstacleImageRef(obstacleIndex : Int) : Int{
        return list[obstacleIndex].first().photoRef
    }
}