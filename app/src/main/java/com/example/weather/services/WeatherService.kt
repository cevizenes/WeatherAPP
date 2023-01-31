package com.example.weather.services

import com.example.weather.model.response.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherService {
    //https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid=99c445696736715211b6396fd0505f82
    @GET("data/2.5/weather?&units=metric&appid=99c445696736715211b6396fd0505f82")
    fun getWeatherByCity(
        @Query("q") name : String
    ) : Single<WeatherResponse>
}