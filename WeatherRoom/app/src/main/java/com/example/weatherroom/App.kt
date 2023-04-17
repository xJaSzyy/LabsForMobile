package com.example.weatherroom

import android.app.Application
import androidx.room.Room

class App : Application() {
    override fun onCreate() {
        initDatabase()
        api = WeatherAPI.createAPI()
        super.onCreate()
    }

    companion object {
        lateinit var dataBase: WeatherDataBase
        lateinit var api: WeatherAPI
    }

    private fun initDatabase() {
        dataBase = Room.databaseBuilder(
            this,
            WeatherDataBase::class.java,
            "weather"
        ).build()
    }
}
