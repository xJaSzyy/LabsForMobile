package com.example.logging

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun logging() {
        val logText = findViewById<EditText>(R.id.textField).text.toString()
        Log.v("From EditText", logText)
    }
}
