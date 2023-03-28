package com.example.rickcompanion

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber
import timber.log.Timber.Forest.plant

class MainActivity : AppCompatActivity() {

    private var list = mutableListOf(
        ItemType.CharacterCard(null),
        ItemType.ButtonObject("Загрузить еще")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mService: RetrofitServices? = Common.retrofitService
        if (mService != null) {
            api = mService
        }

        plant(Timber.DebugTree())

        val recyclerList: RecyclerView = findViewById(R.id.rview)
        val adapter = Adapter(list)
        val layoutManager = LinearLayoutManager(this)
        recyclerList.layoutManager = layoutManager
        recyclerList.adapter = adapter

        val model = ViewModelProvider(this)[ViewModel::class.java]
        model.liveData.observe(this) {
            fetchDataFromNetwork(it)
        }

        model.getCharacterList()
    }

    private fun fetchDataFromNetwork(newData: List<DataModel.CharacterResults>) {
        list.removeAll { it is ItemType.ButtonObject }
        val adapter = Adapter(list)
        adapter.submitList(newData)
        val v = ViewModelProvider(this)[ViewModel::class.java]
        val recyclerList: RecyclerView = findViewById(R.id.rview)
        recyclerList.adapter = adapter
        if (v.page == 1) {
            list.removeAt(0)
        }
        else {
            recyclerList.scrollToPosition(adapter.itemCount - 24)
        }

        val hasNextPage = v.page+1 != v.maxPage
        Timber.tag("maxPage").d(v.maxPage.toString())

        if (hasNextPage) {
            list.add(ItemType.ButtonObject("Загрузить еще"))
        }
    }

    companion object {
        lateinit var api: RetrofitServices
    }
}
