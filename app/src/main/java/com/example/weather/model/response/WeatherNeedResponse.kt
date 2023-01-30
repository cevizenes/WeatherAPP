package com.example.weather.model.response

data class WeatherNeedResponse(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)