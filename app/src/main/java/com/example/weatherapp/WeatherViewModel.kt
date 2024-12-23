package com.example.weatherapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.Repo
import com.example.weatherapp.data.WeatherDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class WeatherViewModel(
    val repo: Repo
):ViewModel() {

    val isLoading=MutableLiveData<Boolean>( false)


    val weatherLiveData=MutableLiveData<WeatherDataModel>()

    fun getWeather(city:String){
        viewModelScope.launch(Dispatchers.IO){

        isLoading.postValue(true)
        val response=repo.getWeatherDetails(city)
            if(response.isSuccessful){
                weatherLiveData.postValue(response.body())
                isLoading.postValue(false)
            }
        }

    }
}