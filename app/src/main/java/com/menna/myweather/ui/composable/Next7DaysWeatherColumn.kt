package com.menna.myweather.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.menna.myweather.R
import com.menna.myweather.ui.theme.UrbanistFont
import com.menna.myweather.ui.viewModel.NextDayWeather

@Composable
fun Next7DaysWeatherColumn(
    nextDaysColor: Color,
    daysBorderColor: Color,
    days: List<NextDayWeather>,
    arrowTint: Color,
    dayColor: Color,
    temperatureColor: Color,
    nextDayBorderColor: Color
) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Next 7 days",
            color = nextDaysColor,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = UrbanistFont,
            letterSpacing = 0.25.sp,
            lineHeight = 20.sp,
            modifier = Modifier.padding(top=24.dp)
        )
        Column(
            modifier = Modifier
                .border(1.dp, color = daysBorderColor, shape = RoundedCornerShape(24.dp))
                .padding(vertical = 4.dp)
        ) {
            days.forEachIndexed { index, dayInfo ->
                Column {
                    NextDayRow(
                        day = dayInfo.day,
                        dayColor = dayColor,
                        nextDayIcon = painterResource(id = dayInfo.weatherIconResId),
                        arrowTint = arrowTint,
                        temperatureColor = temperatureColor,
                        dayMaxTemperature = dayInfo.maxTemp,
                        dayMinTemperature = dayInfo.minTemp
                    )
                    if (index != days.lastIndex) {
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(nextDayBorderColor)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNext7DaysWeatherColumn() {
    val dummyDays = listOf(
        NextDayWeather("Monday", R.drawable.light_clear_sky, 30, 21),
        NextDayWeather("Tuesday", R.drawable.light_clear_sky, 31, 22),
        NextDayWeather("Wednesday", R.drawable.light_clear_sky, 29, 20),
        NextDayWeather("Thursday", R.drawable.light_clear_sky, 28, 19),
        NextDayWeather("Friday", R.drawable.light_clear_sky, 27, 18),
        NextDayWeather("Saturday", R.drawable.light_clear_sky, 26, 17),
        NextDayWeather("Sunday", R.drawable.light_clear_sky, 25, 16)
    )

    Next7DaysWeatherColumn(
        nextDaysColor = Color.Black,
        daysBorderColor = Color.Gray,
        days = dummyDays,
        arrowTint = Color(0xFF00619D),
        dayColor = Color.Black,
        temperatureColor = Color.Black,
        nextDayBorderColor = Color.Gray,
        )
}
