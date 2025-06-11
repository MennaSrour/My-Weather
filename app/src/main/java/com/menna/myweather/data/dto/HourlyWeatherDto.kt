package com.menna.myweather.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HourlyWeatherDto(
    @SerialName("time")
    val time: List<String>,
    @SerialName("temperature_2m")
    val temperature: List<Double>,
    @SerialName("weather_code")
    val weatherCodes: List<Int>,
    @SerialName("relative_humidity_2m")
    val humidity: List<Double>,
    @SerialName("wind_speed_10m")
    val windSpeed: List<Double>,
    @SerialName("apparent_temperature")
    val apparentTemperature: List<Double>,
    @SerialName("precipitation_probability")
    val precipitationProbability: List<Double>,
    @SerialName("is_day")
    val isDay: List<Int>,
)