package com.menna.myweather.data.remote

import com.menna.myweather.data.dto.WeatherDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class WeatherApiImpl(private val client: HttpClient) : WeatherApi {
    override suspend fun getWeather(latitude: Double, longitude: Double): WeatherDto {
        return client.get(WeatherApiParameters.WEATHER_API_URL) {
            parameter("latitude", latitude)
            parameter("longitude", longitude)
            parameter("current",WeatherApiParameters.currentWeatherFields.joinToString(","))
            parameter("hourly", WeatherApiParameters.hourlyWeatherFields.joinToString(","))
            parameter("daily", WeatherApiParameters.dailyWeatherFields.joinToString(","))
            parameter("timezone", "auto")
            parameter("forecast_days", 8)
        }.body()
    }
}
object WeatherApiParameters{
    const val WEATHER_API_URL ="https://api.open-meteo.com/v1/forecast"
    val currentWeatherFields = listOf(
        "temperature_2m",
        "weather_code",
        "wind_speed_10m",
        "relative_humidity_2m",
        "apparent_temperature",
        "is_day",
        "precipitation",
        "rain",
        "showers",
        "pressure_msl"
    )

    val hourlyWeatherFields = listOf(
        "temperature_2m",
        "weather_code",
        "relative_humidity_2m",
        "wind_speed_10m",
        "apparent_temperature",
        "precipitation_probability",
        "is_day"
    )

    val dailyWeatherFields = listOf(
        "weather_code",
        "rain_sum",
        "temperature_2m_max",
        "temperature_2m_min",
        "uv_index_max",
        "apparent_temperature_max",
        "apparent_temperature_min"
    )
}
