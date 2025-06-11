package com.menna.myweather.di

import com.menna.myweather.data.repository.WeatherRepoImpl
import com.menna.myweather.domain.repository.WeatherRepo
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repoModule = module {
    single<WeatherRepo> { WeatherRepoImpl(get(),get(),get()) }
}
