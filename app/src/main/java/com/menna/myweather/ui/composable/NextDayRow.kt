package com.menna.myweather.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.menna.myweather.R
import com.menna.myweather.ui.theme.UrbanistFont

@Composable
fun NextDayRow(
    day: String,
    dayColor: Color,
    nextDayIcon: Painter,
    arrowTint: Color,
    temperatureColor: Color,
    dayMaxTemperature: Int,
    dayMinTemperature: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = day,
            color = dayColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = UrbanistFont,
            modifier = Modifier.weight(1f)
        )
        Box(
            modifier=Modifier.size(91.dp,45.dp)
        ) {
            Image(
                painter = nextDayIcon,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp).align(Alignment.Center)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.wrapContentWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_up),
                    contentDescription = null,
                    modifier = Modifier.size(14.dp),
                    tint = arrowTint
                )
                Text(
                    text = "$dayMaxTemperature°C",
                    color = temperatureColor,
                    fontFamily = UrbanistFont,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
            }

            Image(
                painter = painterResource(id = R.drawable.line),
                contentDescription = null,
                modifier = Modifier
                    .height(16.dp)
                    .width(1.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_down),
                    contentDescription = null,
                    modifier = Modifier.size(14.dp),
                    tint = arrowTint
                )
                Text(
                    text = "$dayMinTemperature°C",
                    color = temperatureColor,
                    fontFamily = UrbanistFont,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
            }
        }
    }

}

@Preview
@Composable
fun PreviewNextDayRow() {
    NextDayRow(
        day = "Monday",
        dayColor = Color.Black,
        nextDayIcon = painterResource(id = R.drawable.light_clear_sky),
        arrowTint = Color(0xFF00619D),
        temperatureColor = Color.Black,
        dayMaxTemperature = 30,
        dayMinTemperature = 21
    )
}