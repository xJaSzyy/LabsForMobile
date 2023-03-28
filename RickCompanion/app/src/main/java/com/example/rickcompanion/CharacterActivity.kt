package com.example.rickcompanion

import CharacterAdapter
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

@Suppress("UNCHECKED_CAST")
class CharacterActivity : AppCompatActivity() {
    var adapter: CharacterAdapter? = null
    lateinit var episode: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)

        Timber.plant(Timber.DebugTree())

        val recyclerView: RecyclerView = findViewById(R.id.rcharview)
        recyclerView.layoutManager = LinearLayoutManager(this)

        episode = intent.getStringArrayListExtra("episode") as ArrayList<String>
        for (i in episode.indices) {
            episode[i] = episode[i].replace("https://rickandmortyapi.com/api/episode/", "")
        }

        getEpisodeList()
    }

    @SuppressLint("SuspiciousIndentation")
    private fun getEpisodeList() {
        MainActivity.api.getEpisodeList(episode as ArrayList<Int>).enqueue(object : Callback<List<DataModel.EpisodeResults>> {

            override fun onResponse(call: Call<List<DataModel.EpisodeResults>>, response: Response<List<DataModel.EpisodeResults>>) {
                response.body().let {
                    adapter = CharacterAdapter(it)
                    val recyclerList: RecyclerView = findViewById(R.id.rcharview)
                    recyclerList.adapter = adapter
                }
            }

            override fun onFailure(call: Call<List<DataModel.EpisodeResults>>, t: Throwable) {
                Timber.tag("CharacterActivity").d("NO %s", t.toString())
            }
        })
    }
}