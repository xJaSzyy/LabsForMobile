package com.example.weatherroom

import androidx.room.*

@Dao
interface WeatherDao {
    @Insert
    suspend fun saveWeather(vararg weather: WeatherEntity)

    @Query(value = "Select * from WeatherEntity")
    suspend fun getAllWeather() : WeatherEntity

    @Query("SELECT COUNT(*) FROM WeatherEntity WHERE dtTxt = :date")
    suspend fun getWeatherCountByDate(date: String): Int
}