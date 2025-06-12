package com.menna.myweather.ui.screen

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.menna.myweather.R
import com.menna.myweather.domain.model.DailyWeatherDetails
import com.menna.myweather.domain.model.DayMode
import com.menna.myweather.domain.model.HourlyWeather
import com.menna.myweather.ui.composable.CardsInfoGrid
import com.menna.myweather.ui.composable.HourlyWeatherRow
import com.menna.myweather.ui.composable.LocationInfo
import com.menna.myweather.ui.composable.Next7DaysWeatherColumn
import com.menna.myweather.ui.composable.TemperatureInfo
import com.menna.myweather.ui.theme.DarkColor
import com.menna.myweather.ui.theme.LightColor
import com.menna.myweather.ui.viewModel.WeatherStateUi
import com.menna.myweather.ui.mappers.mapWeatherStateToIconRes
import com.menna.myweather.ui.mappers.toCardInfoList
import com.menna.myweather.ui.mappers.toHourlyWeatherData
import com.menna.myweather.ui.mappers.toNextDayWeather
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import com.google.accompanist.permissions.*
import com.menna.myweather.ui.viewModel.WeatherViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = koinViewModel()
) {
    val permissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)
    val weatherState by viewModel.weather.collectAsState()

    LaunchedEffect(Unit) {
        if (!permissionState.status.isGranted) {
            permissionState.launchPermissionRequest()
        }
    }

    when {
        weatherState.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        weatherState.error != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Error: ${weatherState.error}",
                )
            }
        }

        weatherState.currentDay != null -> {
            Scaffold { innerPadding ->
                Weather(
                    weatherState = weatherState,
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                )
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Weather(
    weatherState: WeatherStateUi,
    modifier: Modifier = Modifier

) {
    val currentDay = weatherState.currentDay
    val nextDays = weatherState.nextDays.map { it.toNextDayWeather() }

    if (currentDay == null) return

    val mode = when (currentDay.dayMode) {
        DayMode.Night -> DarkColor
        DayMode.Day -> LightColor
    }
    val scrollState = rememberLazyListState()

    val scrollOffset = scrollState.firstVisibleItemScrollOffset.toFloat()
    val scrollProgress = (scrollOffset / 300f).coerceIn(0f, 1f)

    LazyColumn(
        state = scrollState,
        modifier = modifier
            .fillMaxSize()
            .background(brush = Brush.linearGradient(mode.backgroundGradient))
    ) {
        item {
            LocationInfo(
                cityName = currentDay.cityName,
                cityNameColor = mode.cityNameColor
            )
        }
        item {
            TemperatureInfo(
                currentDayWeatherImage = if (LocalInspectionMode.current) {
                    painterResource(id = R.drawable.light_clear_sky)
                } else {
                    painterResource(
                        id = mapWeatherStateToIconRes(
                            currentDay.weatherState,
                            currentDay.dayMode
                        )
                    )
                },
                currentDayTemperature = currentDay.currentTemperature,
                currentDayWeatherState = currentDay.weatherState,
                currentDayMaxTemperature = currentDay.maxTemperature,
                currentDayMinTemperature = currentDay.minTemperature,
                temperatureInfoIconTint = mode.temperatureIconTint,
                currentDayWeatherStateColor = mode.temperatureStateColor,
                currentDayTemperatureColor = mode.temperatureTextColor,
                currentDayMinTemperatureColor = mode.temperatureMinColor,
                blurColor = mode.blurColor,
                scrollProgress = scrollProgress
            )
        }
        item {
            CardsInfoGrid(
                cardInfo = currentDay.toCardInfoList(),
                cardBackgroundColor = mode.cardBackground,
                cardBorderColor = mode.cardBorderColor,
                cardTextColor = mode.cardTextColor,
                cardLabelColor = mode.cardLabelColor,
                cardIconTint = mode.cardIconTint
            )
        }
        item {
            HourlyWeatherRow(
                hourlyWeatherList = currentDay.hourly.map {
                    it.toHourlyWeatherData(weatherState = it.weatherState, dayMode = it.dayMode)
                },
                hourTextColor = mode.hourTextColor,
                hourColor = mode.hourColor,
                hourlyWeatherCardColor = mode.hourlyWeatherCardColor,
                cardBorderColor = mode.cardBorderColor,
                todayColor = mode.todayColor
            )
        }
        item {
            Next7DaysWeatherColumn(
                nextDaysColor = mode.nextDaysColor,
                daysBorderColor = mode.daysBorderColor,
                days = nextDays,
                arrowTint = mode.arrowTint,
                dayColor = mode.dayColor,
                temperatureColor = mode.temperatureColor,
                nextDayBorderColor = mode.nextDayBorderColor
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun WeatherPreview() {
    Weather(
        weatherState = WeatherStateUi(
            currentDay = DailyWeatherDetails(
                cityName = "Cairo",
                weatherState = "clear sky",
                currentTemperature = 30.00,
                maxTemperature = 33.00,
                minTemperature = 24.00,
                windSpeedKmh = 15.00,
                humidityPercentage = 50.00,
                rainProbability = 20.00,
                uvIndex = 5,
                pressure = 1013,
                feelsLikeTemperature = 31.00,
                dayMode = DayMode.Day,
                hourly = listOf(
                    HourlyWeather("12:00", 33.00, "clear sky", DayMode.Day),
                    HourlyWeather("15:00", 44.00, "partly cloudy", DayMode.Day)
                ),
                date = "2025-06-11"
            ),
            nextDays = emptyList()
        ),
        modifier = Modifier
            .fillMaxSize()
    )
}