package com.menna.myweather

import android.app.Application
import com.menna.myweather.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class MyWeather: Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidContext(this@MyWeather)
            modules(appModules)
        }
    }
}