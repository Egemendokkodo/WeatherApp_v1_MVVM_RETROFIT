package com.uygulamalarim.weatherapp_v2.WeatherService.service

import com.uygulamalarim.weatherapp_v2.WeatherService.model.WeatherModal
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/weather?&units=metric&APPID=04a42b96398abc8e4183798ed22f9485")
    fun getData(
        @Query("q") cityName:String
    ):Single<WeatherModal>





}