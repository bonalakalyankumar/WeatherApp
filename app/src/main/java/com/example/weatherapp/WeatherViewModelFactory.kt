package com.example.weatherapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.data.Repo

class WeatherViewModelFactory(
    val repo: Repo
) :ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WeatherViewModel(repo) as T
    }
}