package com.menna.myweather.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
            } catch (e: Exception) {
                _weatherState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message
                    )
                }
            }
        }
    }
}
