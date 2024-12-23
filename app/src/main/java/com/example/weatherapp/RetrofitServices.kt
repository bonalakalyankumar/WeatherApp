package com.example.weatherapp

import com.example.weatherapp.data.WeatherDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices{
    @GET("v1/current.json?key=47efc58244c54491aca15958241112")
    suspend fun getWeatherDetails(
        @Query("q")city:String
    ):Response<WeatherDataModel>
}