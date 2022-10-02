package com.example.logging

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber
import timber.log.Timber.Forest.plant

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        plant(Timber.DebugTree())
    }

    fun loggingWithLog(view: View) {
        val logText = findViewById<EditText>(R.id.textField).text.toString()
        Timber.tag("From EditText").v(logText)
    }

    fun loggingWithTimber(view: View) {
        val logText = findViewById<EditText>(R.id.textField).text.toString()
        Timber.d(logText)
    }
}
