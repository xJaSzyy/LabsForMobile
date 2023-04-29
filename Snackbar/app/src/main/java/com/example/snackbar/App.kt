package com.example.snackbar

import android.app.Application

class App: Application() {
    override fun onCreate() {
        api = WeatherAPI.createAPI()
        super.onCreate()
    }

    companion object {
        lateinit var api: WeatherAPI
    }
}