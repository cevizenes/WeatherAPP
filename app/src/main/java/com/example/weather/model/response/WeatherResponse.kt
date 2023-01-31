package com.example.weather.model.response

data class WeatherResponse(
    val base: String,
    val clouds: CloudsResponse,
    val cod: Int,
    val coord: CoordResponse,
    val dt: Int,
    val id: Int,
    val main: MainResponse,
    val name: String,
    val snow: SnowResponse,
    val sys: SysResponse,
    val timezone: Int,
    val visibility: Int,
    val weather: List<WeatherNeedResponse>,
    val wind: WindResponse

)