package com.menna.myweather.data.mapper

import com.menna.myweather.data.LocationUnavailableException
import com.menna.myweather.domain.exceptions.LocationPermissionDenied
import com.menna.myweather.domain.exceptions.LocationUnavailable
import com.menna.myweather.domain.exceptions.UnExpectedLocationException

fun Throwable.toLocationException()= when(this){
    is LocationUnavailableException -> LocationUnavailable()
    is SecurityException -> LocationPermissionDenied()
    else -> UnExpectedLocationException()
}