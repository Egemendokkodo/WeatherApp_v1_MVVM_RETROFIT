package com.uygulamalarim.weatherapp_v2.WeatherService.model


import com.google.gson.annotations.SerializedName

data class Clouds(
    @SerializedName("all")
    val all: Int
)