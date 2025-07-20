package com.menna.myweather.ui.mappers

import android.os.Build
import androidx.annotation.RequiresApi
import com.menna.myweather.R
import com.menna.myweather.domain.model.DailyWeatherDetails
import com.menna.myweather.domain.model.DayMode
import com.menna.myweather.domain.model.HourlyWeather
import com.menna.myweather.ui.viewModel.CardInfo
import com.menna.myweather.ui.viewModel.HourlyWeatherData
import com.menna.myweather.ui.viewModel.NextDayWeather
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale


fun DailyWeatherDetails.toCardInfoList(): List<CardInfo> {
    return listOf(
        CardInfo(
            cardIconImage = R.drawable.wind,
            cardText = "$windSpeedKmh km/h",
            cardLabel = "Wind"
        ),
        CardInfo(
            cardIconImage = R.drawable.humidity,
            cardText = "$humidityPercentage%",
            cardLabel = "Humidity"
        ),
        CardInfo(
            cardIconImage = R.drawable.rain,
            cardText = "$rainProbability%",
            cardLabel = "Rain"
        ),
        CardInfo(
            cardIconImage = R.drawable.uv,
            cardText = uvIndex.toString(),
            cardLabel = "UV Index"
        ),
        CardInfo(
            cardIconImage = R.drawable.arrow_down_card,
            cardText = "${pressure.toInt()} hPa",
            cardLabel = "Pressure"
        ),
        CardInfo(
            cardIconImage = R.drawable.temperature,
            cardText = "${feelsLikeTemperature.toInt()}°C",
            cardLabel = "Feels Like"
        ),
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun DailyWeatherDetails.toNextDayWeather(): NextDayWeather {
    val localDate = LocalDate.parse(this.date, DateTimeFormatter.ISO_DATE)
    val dayName = localDate.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH)

    return NextDayWeather(
        day = dayName,
        weatherIconResId = mapWeatherStateToIconRes(weatherState, DayMode.Day),
        maxTemp = maxTemperature.toInt(),
        minTemp = minTemperature.toInt()
    )
}

fun HourlyWeather.toHourlyWeatherData(weatherState: String, dayMode: DayMode): HourlyWeatherData {
    return HourlyWeatherData(

        hourTemperature = "${currentTemperature.toInt()}°C",
        hour = time,
        hourlyWeatherCardImage = mapWeatherStateToIconRes(weatherState, dayMode),
    )
}

fun mapWeatherStateToIconRes(state: String, dayMode: DayMode): Int {
    return when (state.lowercase()) {
        "clear sky" -> if (dayMode == DayMode.Day) R.drawable.light_clear_sky else R.drawable.night_clear_sky
        "mainly clear" -> if (dayMode == DayMode.Day) R.drawable.light_mainly_clear else R.drawable.night_mainly_clear
        "partly cloudy" -> if (dayMode == DayMode.Day) R.drawable.light_partialy_cloudy else R.drawable.night_partly_cloudy
        "overcast" -> if (dayMode == DayMode.Day) R.drawable.light_overcast else R.drawable.night_overcast

        "fog" -> if (dayMode == DayMode.Day) R.drawable.light_fog else R.drawable.night_fog
        "depositing rime fog" -> if (dayMode == DayMode.Day) R.drawable.light_fog else R.drawable.night_fog

        "drizzle light" -> if (dayMode == DayMode.Day) R.drawable.light_drizzle_light else R.drawable.night_drizzle_light
        "drizzle moderate" -> if (dayMode == DayMode.Day) R.drawable.light_drizzle_moderate else R.drawable.night_drizzle_moderate
        "drizzle dense" -> if (dayMode == DayMode.Day) R.drawable.light_drizzle_intensity else R.drawable.night_drizzle_intensity

        "freezing drizzle light" -> if (dayMode == DayMode.Day) R.drawable.light_freezing_drizzle_light else R.drawable.night_freezing_drizzle_light
        "freezing drizzle dense" -> if (dayMode == DayMode.Day) R.drawable.light_freezing_drizzle_intensity else R.drawable.night_freezing_drizzle_intensity

        "rain slight" -> if (dayMode == DayMode.Day) R.drawable.light_rain_slight else R.drawable.night_rain_slight
        "rain moderate" -> if (dayMode == DayMode.Day) R.drawable.light_rain_moderate else R.drawable.night_rain_moderate
        "rain heavy" -> if (dayMode == DayMode.Day) R.drawable.light_rain_intensity else R.drawable.night_rain_intensity

        "freezing rain light" -> if (dayMode == DayMode.Day) R.drawable.light_freezing_rain_light else R.drawable.night_freezing_rain_light
        "freezing rain heavy" -> if (dayMode == DayMode.Day) R.drawable.light_freezing_rain_heavy else R.drawable.night_freezing_rain_heavy

        "snow fall slight" -> if (dayMode == DayMode.Day) R.drawable.light_snow_fall_light else R.drawable.night_snowfall_slight
        "snow fall moderate" -> if (dayMode == DayMode.Day) R.drawable.light_snow_fall_moderate else R.drawable.night_snowfall_moderate
        "snow fall heavy" -> if (dayMode == DayMode.Day) R.drawable.light_snowfall_heavy else R.drawable.night_snowfall_heavy

        "snow grains" -> if (dayMode == DayMode.Day) R.drawable.light_snow_grains else R.drawable.night_snow_grains

        "rain showers slight" -> if (dayMode == DayMode.Day) R.drawable.light_rain_shower_slight else R.drawable.night_rain_shower_slight
        "rain showers moderate" -> if (dayMode == DayMode.Day) R.drawable.light_rain_shower_moderate else R.drawable.night_rain_shower_moderate
        "rain showers violent" -> if (dayMode == DayMode.Day) R.drawable.light_rain_shower_violent else R.drawable.night_rain_shower_violent

        "snow showers slight" -> if (dayMode == DayMode.Day) R.drawable.light_snow_shower_slight else R.drawable.night_snow_shower_slight
        "snow showers heavy" -> if (dayMode == DayMode.Day) R.drawable.light_snow_shower_heavy else R.drawable.night_snow_shower_heavy

        "thunderstorm" -> if (dayMode == DayMode.Day) R.drawable.light_thunderstorm_moderate else R.drawable.night_thunderstorm_moderate
        "thunderstorm with hail" -> if (dayMode == DayMode.Day) R.drawable.light_thunderstorm_with_slight_hail else R.drawable.night_thunderstorm_with_slight_hail

        else -> if (dayMode == DayMode.Day) R.drawable.light_clear_sky else R.drawable.night_clear_sky
    }

}

suspend fun fetchUser(): String {
    delay(1000)
    return "Menna"
}

