package com.menna.myweather.data.location

import android.health.connect.datatypes.ExerciseRoute.Location

interface LocationService {
    suspend fun getCurrentLocation(): android.location.Location
}