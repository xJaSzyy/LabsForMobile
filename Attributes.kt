package com.example.attributes

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun changeTextColor(view: View) {
        val text: EditText = findViewById(R.id.textField)
        val button: Button = view as Button
        val buttonText: String = button.text.toString()
        if (buttonText == "Черный текст") {
            text.setTextColor(Color.BLACK)
        }
        else if (buttonText == "Красный текст") {
            text.setTextColor(Color.RED)
        }
    }

    fun changeBackgroundColor(view: View) {
        val text: EditText = findViewById(R.id.textField)
        val button: Button = view as Button
        val buttonText: String = button.text.toString()
        if (buttonText == "Белый фон") {
            text.setBackgroundColor(Color.WHITE)
        }
        else if (buttonText == "Желтый фон") {
            text.setBackgroundColor(Color.YELLOW)
        }
    }

    fun setTextSmallSize(view: View) {
        val text: EditText = findViewById(R.id.textField)
        text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8f)
    }

    fun setTextBigSize(view: View) {
        val text: EditText = findViewById(R.id.textField)
        text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24f)
    }

}
