package com.example.rickcompanion

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber


class ViewModel : ViewModel() {
    var liveData = MutableLiveData<List<DataModel.CharacterResults>>()

    var page: Int = 1
    var maxPage: Int = 1
    fun getCharacterList() {
        MainActivity.api.getCharacterList(page).enqueue(object : Callback<DataModel.Character> {
            override fun onResponse(call: Call<DataModel.Character>, response: Response<DataModel.Character>) {
                response.body().let {
                    Timber.tag("getList").d(response.body().toString())
                    maxPage = response.body()?.info?.pages ?: page
                    liveData.value = response.body()?.results
                }
            }

            override fun onFailure(call: Call<DataModel.Character>, t: Throwable) {
                Timber.tag("MainActivity").d("NO% s", t.toString())
            }
        })
    }
}