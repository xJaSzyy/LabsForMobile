package com.example.weatherroom

fun WeatherEntity.toDomain() = Weather(
    temperature = temperature, pressure = pressure, dtTxt = dtTxt, iconURL = iconURL
)

fun WeatherNW.DataWeather.toDomain() = Weather(
    temperature = this.main.temp, pressure = this.main.pressure, dtTxt = this.dtTxt, iconURL = this.weather.first().icon
)

fun Weather.toEntity() = WeatherEntity(
    temperature = temperature, pressure = pressure, dtTxt = dtTxt, iconURL = iconURL
)