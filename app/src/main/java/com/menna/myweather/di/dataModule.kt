package com.menna.myweather.di

import com.menna.myweather.data.dataSource.WeatherDataSource
import com.menna.myweather.data.dataSource.WeatherDataSourceImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule= module {
    singleOf(::WeatherDataSourceImpl) bind WeatherDataSource::class
}
