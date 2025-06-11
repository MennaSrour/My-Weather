package com.menna.myweather.domain.usecase

import com.menna.myweather.domain.model.DailyWeatherDetails
import com.menna.myweather.domain.repository.WeatherRepo

class GetWeatherUseCase(
    private val repo: WeatherRepo
) {
    suspend operator fun invoke(): List<DailyWeatherDetails>{
        return repo.getWeatherForecast()
    }
}