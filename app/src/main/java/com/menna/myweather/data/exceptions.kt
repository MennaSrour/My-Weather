package com.menna.myweather.data

class LocationUnavailableException(message: String = "Location is unavailable") : Exception(message)

class PermissionNotGrantedException(message: String = "Location permission not granted") : Exception(message)

class LocationSettingsDisabledException(message: String = "Location settings are disabled") : Exception(message)
