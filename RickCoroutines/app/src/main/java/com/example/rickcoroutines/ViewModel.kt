package com.example.rickcoroutines

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.await


@OptIn(DelicateCoroutinesApi::class)
class ViewModel : ViewModel() {
    var liveData = MutableLiveData<List<DataModel.CharacterResults>>()

    var page: Int = 1
    var maxPage: Int = 1
    fun getCharacterList() {
        GlobalScope.launch(Dispatchers.Main) {
            val mService: RetrofitServices? = Common.retrofitService
            val result = mService?.getCharacterList(page)
            if (result?.isSuccessful == true) {
                liveData.value = result.body()?.results
            }
        }
    }
}