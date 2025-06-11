package com.menna.myweather.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.menna.myweather.R
import com.menna.myweather.ui.theme.BlackColor
import com.menna.myweather.ui.theme.CyainColor
import com.menna.myweather.ui.theme.WhiteColor
import com.menna.myweather.ui.viewModel.HourlyWeatherData

@Composable
fun HourlyWeatherRow(
    hourlyWeatherList: List<HourlyWeatherData>,
    hourTextColor: Color,
    hourColor: Color,
    hourlyWeatherCardColor: Color,
    cardBorderColor: Color

) {
    LazyRow(
        modifier = Modifier.padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(hourlyWeatherList) { item ->
            HourlyWeatherCard(
                hourlyWeatherCardImage = item.hourlyWeatherCardImage,
                hourlyWeatherCardColor = hourlyWeatherCardColor,
                hourTemperature = item.hourTemperature,
                hourTextColor = hourTextColor,
                hour = item.hour,
                hourColor = hourColor,
                cardBorderColor = cardBorderColor
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun HourlyWeatherRowPreview() {
    val dummyHourlyList = listOf(
        HourlyWeatherData(R.drawable.light_clear_sky, "22°C", "10 AM"),
        HourlyWeatherData(R.drawable.light_partialy_cloudy, "24°C", "11 AM"),
        HourlyWeatherData(R.drawable.light_rain_slight, "21°C", "12 PM"),
        HourlyWeatherData(R.drawable.light_thunderstorm_moderate, "20°C", "1 PM"),
    )

    HourlyWeatherRow(
        hourlyWeatherList = dummyHourlyList,
        hourTextColor = BlackColor,
        hourColor = CyainColor,
        hourlyWeatherCardColor = WhiteColor,
        cardBorderColor = LightGray
    )
}