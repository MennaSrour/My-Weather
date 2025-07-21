package com.menna.myweather.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.menna.myweather.domain.exceptions.LocationException
import com.menna.myweather.domain.exceptions.LocationPermissionDenied
import com.menna.myweather.domain.exceptions.LocationUnavailable
import com.menna.myweather.domain.exceptions.ServerErrorException
import com.menna.myweather.domain.exceptions.UnavailableNetworkException
import com.menna.myweather.domain.exceptions.WeatherException
import com.menna.myweather.domain.usecase.GetWeatherUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private val _weatherState = MutableStateFlow(WeatherStateUi())
    val weather = _weatherState.asStateFlow()

    init {
        loadWeather()
    }

    fun loadWeather() {
        viewModelScope.launch {
            _weatherState.update { it.copy(isLoading = true) }

            try {
                val forecast = getWeatherUseCase()
                _weatherState.update {
                    it.copy(
                        isLoading = false,
                        currentDay = forecast.firstOrNull(),
                        nextDays = forecast.drop(1)
                    )
                }
            } catch (e: WeatherException) {
                _weatherState.update {
                    it.copy(
                        isLoading = false,
                        error = e.toUiErrorMessage()
                    )
                }
            } catch (e: LocationException) {
                _weatherState.update {
                    it.copy(
                        isLoading = false,
                        error = e.toUiErrorMessage()
                    )
                }
            }
        }
    }
}
    private fun WeatherException.toUiErrorMessage(): String {
        return when (this) {
            is UnavailableNetworkException -> "Please check your internet connection"
            is ServerErrorException -> "There is error with server"
            else -> " unknown error happened"
        }
    }

    private fun LocationException.toUiErrorMessage(): String {
        return when (this) {
            is LocationUnavailable -> "Location is unavailable"
            is LocationPermissionDenied -> "no permission to access location"
            else -> "unknown error happened"
        }
    }