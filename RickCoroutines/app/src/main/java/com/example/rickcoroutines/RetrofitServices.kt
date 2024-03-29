package com.example.rickcoroutines

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface RetrofitServices {
    @GET("api/character")
    suspend fun getCharacterList(@Query("page") page: Int): Response<DataModel.Character>

    @GET("api/episode/{id}")
    suspend fun getEpisodeList(@Path("id") id: ArrayList<Int>): Response<List<DataModel.EpisodeResults>>
}