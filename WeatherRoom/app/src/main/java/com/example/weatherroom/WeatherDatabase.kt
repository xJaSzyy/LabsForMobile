package com.example.weatherroom

import android.content.Context
import androidx.room.*

@Database(
    entities = [WeatherEntity::class],
    version = 3
)
abstract class WeatherDataBase: RoomDatabase(){
    abstract fun weatherDao(): WeatherDao
}