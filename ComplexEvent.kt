package com.example.complexevent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun saveButton(view: View) {
        val editText: EditText = findViewById(R.id.editText)
        val checkBox: CheckBox = findViewById(R.id.checkBox)
        val textView: TextView = findViewById(R.id.textView)
        val bar: ProgressBar = findViewById(R.id.progressBar)
        if (checkBox.isChecked) {
            bar.progress += 10
            textView.text = editText.text
        }
    }
}
