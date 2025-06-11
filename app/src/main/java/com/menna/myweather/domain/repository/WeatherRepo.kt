package com.menna.myweather.domain.repository

import com.menna.myweather.domain.model.DailyWeatherDetails

interface  WeatherRepo {
    suspend fun getWeatherForecast(): List<DailyWeatherDetails>
}