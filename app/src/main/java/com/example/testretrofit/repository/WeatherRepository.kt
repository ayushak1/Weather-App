package com.example.testretrofit.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testretrofit.apii.WeatherService
import com.example.testretrofit.models.WeatherClass

class WeatherRepository(private val weatherService: WeatherService) {

    private val weatherLiveData  = MutableLiveData<WeatherClass>()

    val weather : LiveData<WeatherClass>
    get() = weatherLiveData

    suspend fun getWeather(city:String){
        val result = weatherService.getWeather(city)
        if(result?.body() != null){
            weatherLiveData.postValue(result.body())
        }
    }
}