package com.example.weatherroom

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MainViewModel : ViewModel() {
    private val _weatherList = MutableLiveData<List<Weather>>()
    val weatherList get() = _weatherList

    init {
        loadWeather()
    }

    private fun loadWeather() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = App.api.getForecast(
                Constants.API_CITY,
                Constants.API_KEY,
                Constants.API_UNITS,
                Constants.API_LANG
            )
            try {
                if(response.isSuccessful){
                    val weather = response.body()?.list?.map {
                        it.toDomain()
                    }
                    _weatherList.postValue(weather)

                    weather?.forEach {
                        var count = 0
                        val date = it.dtTxt.split(" ")[0]
                        val aw = App.dataBase.weatherDao().getAllWeather()
                        if (aw.dtTxt == date) {
                            count++
                        }

                        if (count == 0) {
                            App.dataBase.weatherDao().saveWeather(it.toEntity())
                        }
                    }
                }
                else{
                    _weatherList.postValue(listOf(App.dataBase.weatherDao().getAllWeather().toDomain()))
                }
            } catch (e: HttpException) {
                Log.e("aaa", "Exception ${e.message}")
            } catch (e: Throwable) {
                Log.d("ooops", "Something else went wrong: $e")
            }
        }
    }
}