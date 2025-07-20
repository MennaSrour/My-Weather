package com.menna.myweather.data.remote

import com.menna.myweather.data.dto.WeatherDto
import com.menna.myweather.data.mapper.toWeatherException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class WeatherApiImpl(private val client: HttpClient) : WeatherApi {
    override suspend fun getWeather(lat: Double, long: Double): WeatherDto {
        return try {
            client.get(WEATHER_API_URL) {
                parameter("latitude", lat)
                parameter("longitude", long)
                parameter("current", currentWeatherFields.joinToString(","))
                parameter("hourly", hourlyWeatherFields.joinToString(","))
                parameter("daily", dailyWeatherFields.joinToString(","))
                parameter("timezone", "auto")
                parameter("forecast_days", 8)
            }.body()
        } catch (e: Throwable) {
            throw e.toWeatherException()
        }
    }

    companion object {
        const val WEATHER_API_URL = "https://api.open-meteo.com/v1/forecast"
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

}
