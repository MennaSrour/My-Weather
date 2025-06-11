package com.menna.myweather.di

import com.menna.myweather.domain.usecase.GetWeatherUseCase
import org.koin.dsl.module

val useCaseModule= module {
    single { GetWeatherUseCase(get()) }
}
