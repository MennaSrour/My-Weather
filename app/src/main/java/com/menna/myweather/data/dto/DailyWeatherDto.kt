package com.menna.myweather.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyWeatherDto(
    @SerialName("time")
    val time: List<String>,
    @SerialName("weather_code")
    val weatherCodes: List<Int>,
    @SerialName("temperature_2m_max")
    val tempMax: List<Double>,
    @SerialName("temperature_2m_min")
    val tempMin: List<Double>,
    @SerialName("apparent_temperature_max")
    val feelsLikeMax: List<Double>,
    @SerialName("apparent_temperature_min")
    val feelsLikeMin: List<Double>,
    @SerialName("rain_sum")
    val rainSum: List<Double>,
    @SerialName("uv_index_max")
    val uvIndex: List<Double>
)
