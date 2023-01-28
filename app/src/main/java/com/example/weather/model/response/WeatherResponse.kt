package com.example.weather.model.response

import com.example.weather.model.*

data class WeatherResponse(
    val base: String,
    val clouds: CloudsResponse,
    val cod: Int,
    val coord: CoordResponse,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val snow: Snow,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind

)