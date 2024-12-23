package com.example.weatherapp.data

import com.example.weatherapp.RetrofitServices

class Repo(
    val retrofitServices: RetrofitServices
) {
    suspend fun getWeatherDetails(city:String)=retrofitServices.getWeatherDetails(city)
}