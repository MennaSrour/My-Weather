package com.menna.myweather.ui.viewModel

import com.menna.myweather.domain.model.DailyWeatherDetails

data class WeatherStateUi(
    val isLoading: Boolean = false,
    val error: String? = null,
    val currentDay: DailyWeatherDetails? = null,
    val nextDays: List<DailyWeatherDetails> = emptyList()
)

data class NextDayWeather(
    val day: String,
    val weatherIconResId: Int,
    val maxTemp: Int,
    val minTemp: Int
)

data class CardInfo(
    val cardIconImage: Int,
    val cardText: String,
    val cardLabel: String,
)

data class HourlyWeatherData(
    val hourlyWeatherCardImage: Int,
    val hourTemperature: String,
    val hour: String,
)
