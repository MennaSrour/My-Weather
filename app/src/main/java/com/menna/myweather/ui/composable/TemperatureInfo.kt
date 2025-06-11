package com.menna.myweather.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.menna.myweather.R
import com.menna.myweather.ui.theme.TempRangeColor
import com.menna.myweather.ui.theme.UrbanistFont

@Composable
fun TemperatureInfo(
    currentDayWeatherImage: Painter,
    currentDayTemperature: Double,
    currentDayWeatherState: String,
    currentDayMaxTemperature: Double,
    currentDayMinTemperature: Double,
    temperatureInfoIconTint: Color,
    currentDayWeatherStateColor: Color,
    currentDayTemperatureColor: Color,
    currentDayMinTemperatureColor: Color,
    blurColor: Color,
    scrollProgress: Float
) {
    val progress = scrollProgress.coerceIn(0f, 1f)

    val imageStartX = 67.dp
    val imageEndX = 12.dp
    val imageCurrentX = imageStartX + (imageEndX - imageStartX) * progress

    val imageStartWidth = 227.dp
    val imageEndWidth = 124.dp
    val imageCurrentWidth = imageStartWidth + (imageEndWidth - imageStartWidth) * progress

    val imageStartHeight = 200.dp
    val imageEndHeight = 112.dp
    val imageCurrentHeight = imageStartHeight + (imageEndHeight - imageStartHeight) * progress

    val tempInfoStartX = 97.dp
    val tempInfoEndX = 180.dp
    val tempInfoCurrentX = tempInfoStartX + (tempInfoEndX - tempInfoStartX) * progress

    val tempInfoStartY = 312.dp
    val tempInfoEndY = 100.dp
    val tempInfoCurrentY = tempInfoStartY + (tempInfoEndY - tempInfoStartY) * progress

    val containerStartHeight = 455.dp
    val containerEndHeight = 243.dp
    val containerCurrentHeight =
        containerStartHeight + (containerEndHeight - containerStartHeight) * progress

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(containerCurrentHeight)
    ) {

        Box(
            modifier = Modifier
                .offset(x = imageCurrentX, y = 100.dp)
                .size(width = imageCurrentWidth, height = imageCurrentHeight)
        ) {

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .blur(50.dp, BlurredEdgeTreatment.Unbounded)
                    .clip(CircleShape)
                    .background(color = blurColor)
            )


            Image(
                painter = currentDayWeatherImage,
                contentDescription = null,
                modifier = Modifier
                    .matchParentSize()
                    .padding(12.dp)
                    .zIndex(1f)
            )
        }


        Column(
            modifier = Modifier
                .offset(x = tempInfoCurrentX, y = tempInfoCurrentY)
                .width(168.dp),
            horizontalAlignment = if (progress > 0.5f) Alignment.Start else Alignment.CenterHorizontally
        ) {
            Text(
                text = "${currentDayTemperature.toInt()}°C",
                color = currentDayTemperatureColor,
                fontFamily = UrbanistFont,
                fontWeight = FontWeight.SemiBold,
                fontSize = if (progress > 0.5f) 24.sp else 64.sp,
                lineHeight = if (progress > 0.5f) 24.sp else 64.sp,
                letterSpacing = 0.25.sp,
            )

            Text(
                text = currentDayWeatherState,
                color = currentDayWeatherStateColor,
                fontFamily = UrbanistFont,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.25.sp,
                modifier = Modifier.padding(top = if (progress > 0.5f) 4.dp else 8.dp)
            )


            Row(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .background(color = TempRangeColor)
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_up),
                        contentDescription = null,
                        modifier = Modifier.size(12.dp),
                        tint = temperatureInfoIconTint
                    )
                    Text(
                        text = "${currentDayMaxTemperature.toInt()}°C",
                        color = currentDayMinTemperatureColor,
                        fontFamily = UrbanistFont,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        lineHeight = 14.sp,
                        letterSpacing = 0.25.sp,
                    )
                }

                Icon(
                    painter = painterResource(id = R.drawable.line),
                    contentDescription = null,
                    modifier = Modifier.size(12.dp),
                    tint = temperatureInfoIconTint
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_down),
                        contentDescription = null,
                        modifier = Modifier.size(12.dp),
                        tint = temperatureInfoIconTint
                    )
                    Text(
                        text = "${currentDayMinTemperature.toInt()}°C",
                        color = currentDayMinTemperatureColor,
                        fontFamily = UrbanistFont,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        lineHeight = 14.sp,
                        letterSpacing = 0.25.sp,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ViewTemperatureInfo() {
    TemperatureInfo(
        currentDayWeatherImage = painterResource(id = R.drawable.light_clear_sky),
        currentDayTemperature = 24.0,
        currentDayWeatherState = "Partly Cloudy",
        currentDayMaxTemperature = 28.0,
        currentDayMinTemperature = 20.0,
        temperatureInfoIconTint = Color.White,
        currentDayWeatherStateColor = Color.White,
        currentDayTemperatureColor = Color.White,
        currentDayMinTemperatureColor = Color.White,
        blurColor = Color.Blue.copy(alpha = 0.3f),
        scrollProgress = 0f
    )
}