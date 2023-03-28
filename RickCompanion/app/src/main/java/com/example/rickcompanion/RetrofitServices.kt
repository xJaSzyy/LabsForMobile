package com.example.rickcompanion

import retrofit2.Call
import retrofit2.http.*


interface RetrofitServices {
    @GET("api/character")
    fun getCharacterList(@Query("page") page: Int): Call<DataModel.Character>

    @GET("api/episode/{id}")
    fun getEpisodeList(@Path("id") id: ArrayList<Int>): Call<List<DataModel.EpisodeResults>>
}