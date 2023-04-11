package com.example.weatherforecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
class ViewModel : ViewModel() {
    private val _weatherList = MutableLiveData<List<WeatherData.DataWeather>>()
    val weatherList: LiveData<List<WeatherData.DataWeather>> = _weatherList

    init {
        loadFromNetwork()
    }

    private fun loadFromNetwork() {
        GlobalScope.launch(Dispatchers.Main) {
            val result = App.api.getForecast(
                Constants.API_CITY,
                Constants.API_KEY,
                Constants.API_UNITS,
                Constants.API_LANG
            )
            if (result.isSuccessful) {
                _weatherList.value = result.body()!!.list
            }
        }
    }
}

