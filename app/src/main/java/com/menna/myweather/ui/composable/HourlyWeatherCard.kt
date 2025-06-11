package com.menna.myweather.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.menna.myweather.R
import com.menna.myweather.ui.theme.BlackColor
import com.menna.myweather.ui.theme.CyainColor
import com.menna.myweather.ui.theme.DarkGray
import com.menna.myweather.ui.theme.UrbanistFont
import com.menna.myweather.ui.theme.WhiteColor

@Composable
fun HourlyWeatherCard(
    hourlyWeatherCardImage: Int,
    hourlyWeatherCardColor: Color,
    hourTemperature: String,
    hourTextColor: Color,
    hour: String,
    hourColor: Color,
    cardBorderColor: Color
) {

    Box(
        modifier = Modifier
            .height(132.dp)
            .width(88.dp)
    ) {
        Image(
            painter = painterResource(id = hourlyWeatherCardImage),
            contentDescription = null,
            modifier = Modifier
                .width(64.dp)
                .align(Alignment.TopCenter)
                .zIndex(1f)

        )
        Box(
            modifier = Modifier
                .height(120.dp)
                .width(88.dp).align(Alignment.BottomEnd)
                .border(1.dp, color = cardBorderColor, shape = RoundedCornerShape(20.dp))
                .clip(RoundedCornerShape(20.dp))
                .background(color = hourlyWeatherCardColor)
        ) {

            Column(
                modifier = Modifier
                    .padding(top = 62.dp, start = 26.dp)
                    .height(42.dp)
                    .wrapContentWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = hourTemperature,
                    color = hourTextColor,
                    fontFamily = UrbanistFont,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    letterSpacing = 0.25.sp,
                )
                Text(
                    text = hour,
                    color = hourColor,
                    fontFamily = UrbanistFont,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    letterSpacing = 0.25.sp,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HourlyWeatherCardPreview() {
    HourlyWeatherCard(
        hourlyWeatherCardImage = R.drawable.light_clear_sky,
        hourlyWeatherCardColor = WhiteColor,
        hourTemperature = "26°C",
        hourTextColor = BlackColor,
        hour = "12 PM",
        hourColor = CyainColor,
        cardBorderColor = DarkGray
    )
}



