package com.example.rickcoroutines

import CharacterAdapter
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.await
import timber.log.Timber
import kotlin.collections.ArrayList

@OptIn(DelicateCoroutinesApi::class)
@Suppress("UNCHECKED_CAST")
class CharacterActivity : AppCompatActivity() {
    lateinit var adapter: CharacterAdapter
    lateinit var episode: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)

        Timber.plant(Timber.DebugTree())

        val recyclerView: RecyclerView = findViewById(R.id.rcharview)
        recyclerView.layoutManager = LinearLayoutManager(this)

        episode = intent.getStringArrayListExtra("episode")!!
        for (i in episode.indices) {
            episode[i] = episode[i].replace("https://rickandmortyapi.com/api/episode/", "")
        }

        getEpisodeList(episode as ArrayList<Int>)
    }

    @SuppressLint("SuspiciousIndentation")
    private fun getEpisodeList(episodes: ArrayList<Int>) {
        val mService: RetrofitServices = Common.retrofitService
        GlobalScope.launch(Dispatchers.Main) {
            val result = mService.getEpisodeList(episodes).await()

            adapter = CharacterAdapter(result)
            val recyclerList: RecyclerView = findViewById(R.id.rcharview)
            recyclerList.adapter = adapter
        }
    }
}