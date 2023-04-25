package com.example.ulla_app.dataclasses

data class PhotoInformation(val confidence : Int, val description : String, val locale : String, val mid : String, val score : Float, val topicality : Float)
data class ObstaclePosition(val x : Int ,val y : Int,val timestamp : Int, val photoStringB64 : String, val photoInformation : PhotoInformation)
