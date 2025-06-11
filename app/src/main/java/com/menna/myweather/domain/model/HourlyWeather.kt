package com.menna.myweather.domain.model

data class HourlyWeather(
    val time:String,
    val currentTemperature: Double,
    val weatherState: String,
    val dayMode: DayMode
)

