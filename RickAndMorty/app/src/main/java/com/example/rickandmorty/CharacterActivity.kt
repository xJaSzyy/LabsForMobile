package com.example.rickandmorty

import CharacterAdapter
import RetrofitServices
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import kotlin.collections.ArrayList

class CharacterActivity : AppCompatActivity() {
    lateinit var adapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)

        Timber.plant(Timber.DebugTree())

        val recyclerView: RecyclerView = findViewById(R.id.rcharview)
        recyclerView.layoutManager = LinearLayoutManager(this)

        getEpisodeList()
    }

    val eList: ArrayList<DataModel.EpisodeResults> = arrayListOf()

    @SuppressLint("SuspiciousIndentation")
    private fun getEpisodeList() {
        val mService: RetrofitServices = Common.retrofitService
        for (i in 1..30)
            mService.getEpisodeList(i).enqueue(object : Callback<DataModel.EpisodeResults> {

                override fun onResponse(call: Call<DataModel.EpisodeResults>, response: Response<DataModel.EpisodeResults>) {
                    eList.add(response.body()!!)
                    eList.sortBy { it.id }

                    val newList = filter(eList)

                    adapter = CharacterAdapter(newList)
                    val recyclerList: RecyclerView = findViewById(R.id.rcharview)
                    recyclerList.adapter = adapter
                }

                override fun onFailure(call: Call<DataModel.EpisodeResults>, t: Throwable) {
                    Timber.tag("CharacterActivity").d("NO %s", t.toString())
                }
            })
    }

    fun filter(results: ArrayList<DataModel.EpisodeResults>): ArrayList<DataModel.EpisodeResults> {
        val id: Int = intent.getIntExtra("id", -1)

        val empty: ArrayList<DataModel.EpisodeResults> = arrayListOf()

        for (i in results.indices) {
            if (results[i].characters.contains("https://rickandmortyapi.com/api/character/$id")) {
                val model = results[i]
                empty.add(model)
            }
        }

        Timber.tag("Episode").d(empty.toString())
        return empty
    }


}