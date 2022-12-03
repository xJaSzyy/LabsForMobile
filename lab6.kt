package com.example.clonewar

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import timber.log.Timber
import java.net.URL


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), CellClickListener {
    private val myUrl =
        URL("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=ff49fcd4d4a08aa6aafb6ea3de826464&tags=cat&format=json&nojsoncallback=1")
    private var okHttpClient: OkHttpClient = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.plant(Timber.DebugTree())

        val recyclerView: RecyclerView = findViewById(R.id.rView)

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = Adapter(this, fetchList(), this)

        val mDividerItemDecoration: DividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            (recyclerView.layoutManager as GridLayoutManager).orientation
        )
        recyclerView.addItemDecoration(mDividerItemDecoration);
    }

    data class Photo(
        val id: Long,
        val owner: String = "",
        val secret: String = "",
        val server: Int = 0,
        val farm: Int = 0,
        val title: String = "",
    )

    data class PhotoPage(
        val page: Int = 1,
        val pages: Int = 1,
        val photo: JsonArray,
    )

    data class Wrapper(
        val photos: JsonObject,
        val stat: String = "ok",
    )

    private fun fetchList(): ArrayList<Photo> {
        val list = arrayListOf<Photo>()

        val model1 =
            Photo(52533110914, "36171287@N08", "715948e853", 65535, 66, "Basking in the light")
        val model2 = Photo(52533020149, "45148911@N06", "2f46d0e873", 65535, 66, "SL2S_L1010163_C1")
        val model3 = Photo(52531744785, "61300408@N05", "8623f10180", 65535, 66, "小豆")
        val model4 =
            Photo(52530249735, "97555832@N08", "5f29879aaf", 65535, 66, "Alma&Felicia_02992")
        val model5 = Photo(52525803402,
            "43056966@N07",
            "47e6af2b13",
            65535,
            66,
            "Valldemossa Cat [Valldemossa - 24 August 2022]")
        val model6 = Photo(52525693774, "59831820@N06", "d11563fa6e", 65535, 66, "Leica Q2")

        list.add(model1)
        list.add(model2)
        list.add(model3)
        list.add(model4)
        list.add(model5)
        list.add(model6)

        return list
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            val snack = Snackbar.make(findViewById(R.id.mainLayout),"Картинка добавлена в избранное",Snackbar.LENGTH_LONG)
            snack.setAction("ОТКРЫТЬ", View.OnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(data.getSerializableExtra("link").toString()))
                startActivity(browserIntent)
            })
            snack.show()
        }
    }


    override fun onCellClickListener(context: Context, data: String) {
        val intent = Intent(context, PicViewer::class.java)
        intent.putExtra("picLink", data)
        startActivityForResult(intent, 100)
    }

}

interface CellClickListener {
    fun onCellClickListener(context: Context, data: String)
}



