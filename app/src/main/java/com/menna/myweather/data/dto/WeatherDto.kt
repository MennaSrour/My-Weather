package com.menna.myweather.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDto(
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("longitude")
    val longitude: Double,
    @SerialName("current")
    val currentWeather: CurrentWeatherDto,
    @SerialName("daily")
    val daily: DailyWeatherDto,
    @SerialName("hourly")
    val hourly: HourlyWeatherDto? = null
)
