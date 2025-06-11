package com.menna.myweather.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.menna.myweather.R
import com.menna.myweather.ui.theme.BlackColor
import com.menna.myweather.ui.theme.CyainColor
import com.menna.myweather.ui.theme.TempRangeColor
import com.menna.myweather.ui.theme.UrbanistFont

@Composable
fun CardDetail(
    cardBackgroundColor: Color,
    cardBorderColor: Color,
    cardIconImage: Int,
    cardText: String,
    cardTextColor: Color,
    cardLabel: String,
    cardLabelColor: Color,
    cardIconTint: Color,
    modifier: Modifier= Modifier

    ){

    Column(
        modifier = modifier
            .width(108.dp)
            .wrapContentHeight()
            .border(1.dp,color=cardBorderColor, shape = RoundedCornerShape(24.dp))
            .clip(RoundedCornerShape(24.dp))
            .background(color= cardBackgroundColor)
            .padding(vertical = 16.dp, horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = cardIconImage),
            contentDescription = null,
            modifier = Modifier.size(32.dp),
            tint = cardIconTint
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(2.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = cardText,
                color = cardTextColor,
                fontFamily = UrbanistFont,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.25.sp,
            )

            Text(
                text = cardLabel,
                color = cardLabelColor,
                fontFamily = UrbanistFont,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 14.sp,
                letterSpacing = 0.25.sp,
            )

        }



    }
}

@Composable
@Preview
fun View(){
    CardDetail(
        cardBorderColor = TempRangeColor,
        cardIconImage = R.drawable.temperature,
        cardText = "ERdddede",
        cardTextColor = BlackColor,
        cardLabel = "Wind",
        cardLabelColor = BlackColor,
        cardBackgroundColor = Color.White,
        cardIconTint = CyainColor
    )
}