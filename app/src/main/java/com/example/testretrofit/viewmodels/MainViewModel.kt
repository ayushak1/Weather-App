package com.example.testretrofit.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testretrofit.models.WeatherClass
import com.example.testretrofit.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: WeatherRepository):ViewModel() {

//    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.getWeather("Delhi,India")
//        }
//    }
    fun getWeatherData(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getWeather(city)
        }
    }


    val weather :LiveData<WeatherClass>
    get() = repository.weather
}