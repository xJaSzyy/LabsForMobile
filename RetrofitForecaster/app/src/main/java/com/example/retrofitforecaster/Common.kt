package com.example.retrofitforecaster

import RetrofitServices

object Common {
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(Constants.BASE_URL).create(RetrofitServices::class.java)
}