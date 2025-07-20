package com.menna.myweather.domain.exceptions

open class WeatherException(message: String) : Exception(message)

class UnavailableNetworkException : WeatherException("there is a problem with internet connection")
class ServerErrorException : WeatherException("Server error")
class UnExpectedException : WeatherException("UnKnown error")

open class LocationException(message: String) : Exception(message)
class LocationUnavailable : LocationException("Unavailable location")
class LocationPermissionDenied : LocationException("Location permission denied")
class UnExpectedLocationException : LocationException("Un known location error")
