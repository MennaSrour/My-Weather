package com.menna.myweather.data.dataSource

import com.menna.myweather.data.dto.WeatherDto

interface WeatherDataSource {
    suspend fun getWeather(latitude: Double, longitude: Double): WeatherDto
}