package com.menna.myweather.data.repository

import android.content.Context
import android.location.Geocoder
import com.menna.myweather.data.dataSource.WeatherDataSource
import com.menna.myweather.data.location.LocationService
import com.menna.myweather.data.mapper.toWeatherForecast
import com.menna.myweather.domain.model.DailyWeatherDetails
import com.menna.myweather.domain.repository.WeatherRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Locale


@Suppress("DEPRECATION")
class WeatherRepoImpl(
    private val dataSource: WeatherDataSource,
    private val locationService: LocationService,
    private val context: Context
) : WeatherRepo {

    override suspend fun getWeatherForecast(): List<DailyWeatherDetails> {
        val location = locationService.getCurrentLocation()
        val weatherData = dataSource.getWeather(location.latitude, location.longitude)
        val cityName = getCityName(location.latitude, location.longitude)

        return weatherData.toWeatherForecast().map { daily ->
            daily.copy(cityName = cityName)
        }
    }

    private suspend fun getCityName(lat: Double, lng: Double): String {
        return withContext(Dispatchers.IO) {
            try {
                Geocoder(context, Locale.ENGLISH)
                    .getFromLocation(lat, lng, 1)
                    ?.firstOrNull()
                    ?.locality ?: "Unknown"
            } catch (e: Exception) {
                "Unknown"
            }
        }
    }
}