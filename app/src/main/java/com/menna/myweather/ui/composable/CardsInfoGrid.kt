package com.menna.myweather.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.menna.myweather.R
import com.menna.myweather.ui.theme.BlackColor
import com.menna.myweather.ui.theme.CyainColor
import com.menna.myweather.ui.theme.DarkGray
import com.menna.myweather.ui.theme.WhiteColor
import com.menna.myweather.ui.viewModel.CardInfo

@Composable
fun CardsInfoGrid(
    cardInfo: List<CardInfo>,
    cardBackgroundColor: Color,
    cardBorderColor: Color,
    cardTextColor: Color,
    cardLabelColor: Color,
    cardIconTint: Color
) {
    Column(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            for (i in 0..2) {
                CardDetail(
                    cardIconImage = cardInfo[i].cardIconImage,
                    cardText = cardInfo[i].cardText,
                    cardLabel = cardInfo[i].cardLabel,
                    cardBorderColor = cardBorderColor,
                    cardTextColor = cardTextColor,
                    cardLabelColor = cardLabelColor,
                    cardBackgroundColor = cardBackgroundColor,
                    cardIconTint = cardIconTint,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            for (i in 3..5) {
                CardDetail(
                    cardIconImage = cardInfo[i].cardIconImage,
                    cardText = cardInfo[i].cardText,
                    cardLabel = cardInfo[i].cardLabel,
                    cardBorderColor = cardBorderColor,
                    cardTextColor = cardTextColor,
                    cardLabelColor = cardLabelColor,
                    cardBackgroundColor = cardBackgroundColor,
                    cardIconTint = cardIconTint,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CardsInfoGridPreview() {
    val dummyCards = listOf(
        CardInfo(R.drawable.wind, "25 km/h", "Wind"),
        CardInfo(R.drawable.humidity, "65%", "Humidity"),
        CardInfo(R.drawable.rain, "10%", "Rain"),
        CardInfo(R.drawable.uv, "3", "UV Index"),
        CardInfo(R.drawable.arrow_down_card, "1013 hPa", "Pressure"),
        CardInfo(R.drawable.temperature, "22°C", "Feels Like")
    )

    CardsInfoGrid(
        cardInfo = dummyCards,
        cardBackgroundColor = WhiteColor,
        cardBorderColor = DarkGray,
        cardTextColor = BlackColor,
        cardLabelColor = CyainColor,
        cardIconTint = CyainColor
    )
}