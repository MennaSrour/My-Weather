package com.menna.myweather.domain.model

data class DailyWeatherDetails(
    val date: String,
    val cityName: String,
    val currentTemperature: Double,
    val maxTemperature: Double,
    val minTemperature: Double,
    val weatherState: String,
    val windSpeedKmh: Double,
    val humidityPercentage: Double,
    val rainProbability: Double,
    val uvIndex: Int,
    val pressure: Int,
    val feelsLikeTemperature: Double,
    val dayMode: DayMode,
    val hourly: List<HourlyWeather>)

enum class DayMode {
    Day, Night
}

