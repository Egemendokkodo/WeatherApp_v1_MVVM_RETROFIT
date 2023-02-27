package com.uygulamalarim.weatherapp_v2.WeatherService.service

import com.uygulamalarim.weatherapp_v2.WeatherService.model.WeatherModal
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class WeatherApiService {

    private val BASE_URL="http://api.openweathermap.org/"
    private val API=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(WeatherApi::class.java)
    fun getDataService(cityName:String):Single<WeatherModal>{
        return API.getData(cityName)
    }


}