package com.example.weatherroom

data class Weather(
    val temperature: Double,
    val pressure: Int,
    val dtTxt: String,
    val iconURL: String
)