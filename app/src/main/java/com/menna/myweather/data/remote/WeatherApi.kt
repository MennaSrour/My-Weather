package com.menna.myweather.data.remote

import com.menna.myweather.data.dto.WeatherDto

interface WeatherApi {
    suspend fun getWeather(lat: Double, long: Double): WeatherDto
}