package com.example.internettest

import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.btnHTTP)
        val buttonOk: Button = findViewById(R.id.btnOkHTTP)

        val url = URL("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=ff49fcd4d4a08aa6aafb6ea3de826464&tags=cat&format=json&nojsoncallback=1")

        button.setOnClickListener {
            val policy = ThreadPolicy.Builder()
                .permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val connection = url.openConnection() as HttpURLConnection
            val data = connection.inputStream.bufferedReader().readText()
            Log.d("Flickr cats", data)
        }

        buttonOk.setOnClickListener {
            val okHttpClient = OkHttpClient()

            val request: Request = Request.Builder().url(url).build()
            okHttpClient.newCall(request).enqueue(object: Callback {
                override fun onFailure(call: Call, e: IOException) {
                }
                override fun onResponse(call: Call, response: Response) {
                    val json = response.body()?.string()
                    val txt = JSONObject(json.toString())

                    runOnUiThread {
                        Log.i("Flickr OkCats", txt.toString())
                    }
                }
            })
        }
    }
}
