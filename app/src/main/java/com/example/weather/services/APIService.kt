package com.example.weather.services

import com.example.weather.model.Weather
import com.example.weather.model.WeatherModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class APIService {
    //https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid=99c445696736715211b6396fd0505f82

    private val BASE_URL = "https://api.openweathermap.org/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(API::class.java)

    fun getData(name :String) : Single<WeatherModel>{
        return api.getData(name)
    }
}