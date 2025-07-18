package com.example.weatherapp

import android.os.Bundle

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity

import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.weatherapp.data.Repo
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    private lateinit var repo: Repo
    private lateinit var viewModel: WeatherViewModel
    private lateinit var viewModelFactory: WeatherViewModelFactory
    private lateinit var loader:ProgressBar
    private lateinit var imageIcon:ImageView
    private lateinit var edt_cityName: EditText
    private lateinit var txt_weatherStatus: TextView
    private lateinit var searchbutton:Button
    private lateinit var region:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        init()

        searchbutton.setOnClickListener(){
            val cityName=edt_cityName.text.toString()
            viewModel.getWeather(cityName)
        }

        viewModel.weatherLiveData.observe(this){
            val weather_c=it.current.temp_c
            val weatherStatus=it.current.condition.text

            txt_weatherStatus.text="$weather_c,$weatherStatus"

            val city=it.location.name
            val state=it.location.region

            region.text="$city,$state"

            val ImageLink="https:${it.current.condition.icon}"

            Glide.with(this)
                .load(ImageLink)
                .into(imageIcon)

        }

        viewModel.isLoading.observe(this){
            if(it){
                loader.visibility= View.VISIBLE
            }
            else{
                loader.visibility=View.GONE
            }
        }
    }


    private fun init() {
        repo = Repo(RetrofitBuilder.getRetrofitInstance())
        viewModelFactory=WeatherViewModelFactory(repo)
        viewModel=ViewModelProvider(this,viewModelFactory).get(WeatherViewModel::class.java)

        loader=findViewById(R.id.loader)
        imageIcon=findViewById(R.id.weather_icon)
        edt_cityName=findViewById(R.id.city_name)
        txt_weatherStatus=findViewById(R.id.txt_status)
        searchbutton=findViewById(R.id.btn_get_weather)
        region=findViewById(R.id.region)
    }
}

// this is the main activity
