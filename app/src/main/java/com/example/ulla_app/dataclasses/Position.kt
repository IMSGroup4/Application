package com.example.ulla_app.dataclasses

import kotlinx.serialization.Serializable

@Serializable
data class Position(val timestamp: String, val x: Int, val y: Int)