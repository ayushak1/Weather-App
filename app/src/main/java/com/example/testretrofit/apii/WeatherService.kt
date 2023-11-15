package com.example.testretrofit.apii

import com.example.testretrofit.models.WeatherClass
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("/v1/current.json?key=2e883c09a08b4017b28113816230408")
    suspend fun getWeather(@Query("q") cityName:String) :Response<WeatherClass>


}