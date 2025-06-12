package com.menna.myweather.ui.theme

import androidx.compose.ui.graphics.Color

data class AppColorScheme(
    val backgroundGradient: List<Color>,
    val cardBackground: Color,
    val cardBorderColor: Color,
    val cardTextColor: Color,
    val cardLabelColor: Color,
    val cardIconTint: Color,
    val temperatureTextColor: Color,
    val temperatureMinColor: Color,
    val temperatureStateColor: Color,
    val temperatureIconTint: Color,
    val cityNameColor: Color,
    val hourColor: Color,
    val hourTextColor: Color,
    val hourlyWeatherCardColor: Color,
    val blurColor: Color,
    val nextDaysColor : Color,
    val arrowTint : Color,
    val dayColor : Color,
    val temperatureColor: Color,
    val nextDayBorderColor: Color,
    val daysBorderColor:Color,
    val todayColor: Color
)

val LightColor = AppColorScheme(
    backgroundGradient = listOf(CyainColor, WhiteColor),
    cardBackground = DetailsColor,
    cardBorderColor = TempRangeColor,
    cardTextColor = borderColor,
    cardLabelColor = MeduimGray,
    cardIconTint = CyainColor,
    temperatureTextColor = BlackColor,
    temperatureMinColor = MeduimGray,
    temperatureStateColor = MeduimGray,
    temperatureIconTint = MeduimGray,
    cityNameColor = DarkGray,
    hourColor = MeduimGray,
    hourTextColor = borderColor,
    hourlyWeatherCardColor=DetailsColor,
    blurColor=LightBlurColor,
    nextDaysColor = BlackColor,
    arrowTint =borderColor,
    dayColor = MeduimGray,
    temperatureColor = borderColor,
    nextDayBorderColor = TempRangeColor,
    daysBorderColor =TempRangeColor,
    todayColor=BlackColor
    
)

val DarkColor = AppColorScheme(
    backgroundGradient = listOf(Color.Black,Color.Black),
    cardBackground = DarkCard,
    cardBorderColor = DarkCardBorder,
    cardTextColor = WhiteGray,
    cardLabelColor = BlackGray,
    cardIconTint = CyainColor,
    temperatureTextColor =WhiteColor,
    temperatureMinColor = WhiteGray,
    temperatureStateColor = BlackGray,
    temperatureIconTint = WhiteGray,
    cityNameColor = WhiteColor,
    hourColor = BlackGray,
    hourTextColor = WhiteGray,
    hourlyWeatherCardColor = DarkCard,
    blurColor=DarkBlurColor,
    nextDaysColor = WhiteColor,
    arrowTint =WhiteGray,
    dayColor = BlackGray,
    temperatureColor = WhiteGray,
    nextDayBorderColor = DarkCardBorder,
    daysBorderColor=DarkCardBorder,
    todayColor=WhiteColor
)