package com.example.weather.model.response

data class SysResponse(
    val country: String,
    val id: Int,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
)