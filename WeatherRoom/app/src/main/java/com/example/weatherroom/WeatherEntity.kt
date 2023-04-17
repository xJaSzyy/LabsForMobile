package com.example.weatherroom

import androidx.room.*

@Entity
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val temperature: Double,
    val pressure: Int,
    val dtTxt: String,
    val iconURL: String
)