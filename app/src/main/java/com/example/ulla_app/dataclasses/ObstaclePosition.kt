package com.example.ulla_app.dataclasses

import java.util.Base64

data class PhotoInformation(val confidence : Int,
                            val description : String,
                            val locale : String,
                            val mid : String,
                            val score : Float,
                            val topicality : Float)
data class ObstaclePosition(val x : Int ,
                            val y : Int,
                            val timestamp : Int,
                            val base64_image : String,
                            val infos_image : List<PhotoInformation>)
