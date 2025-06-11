package com.menna.myweather.data.mapper

import com.menna.myweather.data.dto.HourlyWeatherDto
import com.menna.myweather.data.dto.WeatherDto
import com.menna.myweather.domain.model.DailyWeatherDetails
import com.menna.myweather.domain.model.HourlyWeather
import com.menna.myweather.domain.model.DayMode

fun WeatherDto.toWeatherForecast(): List<DailyWeatherDetails> {
    val dailyDetails = this.daily.time.mapIndexed { i, date ->
        val isDay = this.currentWeather.isDay == 1
        val feelsLike = if (isDay) {
            this.daily.feelsLikeMax.getOrElse(i) { this.daily.tempMax[i] }
        } else {
            this.daily.feelsLikeMin.getOrElse(i) { this.daily.tempMin[i] }
        }

        DailyWeatherDetails(
            date = date,
            cityName = "",
            currentTemperature = if (i == 0) this.currentWeather.temperature else this.daily.tempMax[i],
            maxTemperature = this.daily.tempMax[i],
            minTemperature = this.daily.tempMin[i],
            weatherState = mapWeatherCodeToState(this.daily.weatherCodes[i]),
            windSpeedKmh = if (i == 0) this.currentWeather.windSpeed else 0.0,
            humidityPercentage = if (i == 0) this.currentWeather.humidity else 0.0,
            rainProbability = this.daily.rainSum[i],
            uvIndex = this.daily.uvIndex[i].toInt(),
            pressure = if (i == 0) this.currentWeather.pressure.toInt() else 0,
            feelsLikeTemperature = feelsLike,
            dayMode = if (isDay) DayMode.Day else DayMode.Night,
            hourly = this.hourly?.let {
                mapHourlyDtoToDomain(it, date)
            } ?: emptyList()
        )
    }

    return dailyDetails
}

private fun mapHourlyDtoToDomain(
    hourlyDto: HourlyWeatherDto,
    targetDate: String
): List<HourlyWeather> {
    return hourlyDto.time.mapIndexedNotNull { index, time ->
        if (time.startsWith(targetDate)) {
            HourlyWeather(
                time = time.substring(11..15),
                currentTemperature = hourlyDto.temperature[index],
                weatherState = mapWeatherCodeToState(hourlyDto.weatherCodes[index]),
                dayMode = if (hourlyDto.isDay[index]==1) DayMode.Day else DayMode.Night
            )
        } else {
            null
        }
    }
}

private fun mapWeatherCodeToState(weatherCode: Int): String {
    return when (weatherCode) {
        0 -> "Clear sky"
        1 -> "Mainly clear"
        2 -> "Partly cloudy"
        3 -> "Overcast"

        45 -> "Fog"
        48 -> "Depositing rime fog"

        51 -> "Drizzle Light"
        53 -> "Drizzle Moderate"
        55 -> "Drizzle Dense"

        56 -> "Freezing drizzle Light"
        57 -> "Freezing drizzle Dense"

        61 -> "Rain Slight"
        63 -> "Rain Moderate"
        65 -> "Rain Heavy"

        66 -> "Freezing rain Light"
        67 -> "Freezing rain Heavy"

        71 -> "Snow fall Slight"
        73 -> "Snow fall Moderate"
        75 -> "Snow fall Heavy"

        77 -> "Snow grains"

        80 -> "Rain showers Slight"
        81 -> "Rain showers Moderate"
        82 -> "Rain showers Violent"

        85 -> "Snow showers Slight"
        86 -> "Snow showers Heavy"

        95 -> "Thunderstorm"
        96 -> "Thunderstorm with hail Slight"
        99 -> "Thunderstorm with hail Heavy"

        else -> "Unknown"
    }
}

