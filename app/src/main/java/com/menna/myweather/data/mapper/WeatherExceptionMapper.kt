package com.menna.myweather.data.mapper

import com.menna.myweather.domain.exceptions.ServerErrorException
import com.menna.myweather.domain.exceptions.UnExpectedException
import com.menna.myweather.domain.exceptions.UnavailableNetworkException
import com.menna.myweather.domain.exceptions.WeatherException
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.utils.io.errors.IOException

fun Throwable.toWeatherException(): WeatherException = when (this) {
    is ServerResponseException -> ServerErrorException()
    is ConnectTimeoutException, is IOException -> UnavailableNetworkException()
    else -> UnExpectedException()
}