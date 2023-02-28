package com.example.rickandmorty

import Adapter
import RetrofitServices
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.*
import timber.log.Timber
import timber.log.Timber.Forest.plant

class MainActivity : AppCompatActivity() {
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        plant(Timber.DebugTree())

        val recyclerList: RecyclerView = findViewById(R.id.rview)

        recyclerList.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerList.layoutManager = layoutManager

        getCharacterList()
    }

    val cList: ArrayList<DataModel.CharacterResults> = arrayListOf()

    @SuppressLint("SuspiciousIndentation")
    private fun getCharacterList() {
        val mService: RetrofitServices = Common.retrofitService
        for (i in 1..30)
            mService.getCharacterList(i).enqueue(object : Callback<DataModel.CharacterResults> {

                override fun onResponse(call: Call<DataModel.CharacterResults>, response: Response<DataModel.CharacterResults>) {
                    cList.add(response.body()!!)
                    cList.sortBy { it.id }

                    adapter = Adapter(baseContext, cList)
                    val recyclerList: RecyclerView = findViewById(R.id.rview)
                    recyclerList.adapter = adapter
                }

                override fun onFailure(call: Call<DataModel.CharacterResults>, t: Throwable) {
                    Timber.tag("MainActivity").d("NO% s", t.toString())
                }
            })
    }
}
