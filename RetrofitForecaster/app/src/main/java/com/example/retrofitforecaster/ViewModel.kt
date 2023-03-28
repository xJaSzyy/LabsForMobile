package com.example.retrofitforecaster

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ViewModel: ViewModel()  {
    var liveData = MutableLiveData<List<WeatherData.DataWeather>>()

    fun getList() {
        GlobalScope.launch(Dispatchers.Main) {
            val result = Common.retrofitService.getForecast(Constants.API_CITY,
                Constants.API_KEY,
                Constants.API_UNITS,
                Constants.API_LANG)

            if (result.isSuccessful) {
                liveData.value = result.body()?.list!!
            }
        }
    }
}