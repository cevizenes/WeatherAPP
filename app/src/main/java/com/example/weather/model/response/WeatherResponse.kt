package com.example.weather.model.response

data class WeatherResponse(
    val base: String,
    val clouds: CloudsResponse,
    val cod: Int,
    val coord: CoordResponse,
    val dt: Int,
    val id: Int,
    val mainResponse: MainResponse,
    val name: String,
    val snowResponse: SnowResponse,
    val sysResponse: SysResponse,
    val timezone: Int,
    val visibility: Int,
    val weatherNeedResponse: List<WeatherNeedResponse>,
    val windResponse: WindResponse

)