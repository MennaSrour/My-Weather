package com.menna.myweather.data.dataSource

import com.menna.myweather.data.dto.WeatherDto
import com.menna.myweather.data.remote.WeatherApi

class WeatherDataSourceImpl(
    private val api: WeatherApi
) : WeatherDataSource {
    override suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): WeatherDto = api.getWeather(latitude, longitude)

}
