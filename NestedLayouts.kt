package com.example.nestedlayouts

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun rollMethod(view: View) {
        val text1: TextView = findViewById(R.id.textView)
        val text2: TextView = findViewById(R.id.textView2)
        val text3: TextView = findViewById(R.id.textView3)
        val text4: TextView = findViewById(R.id.textView4)
        val text5: TextView = findViewById(R.id.textView5)
        val text6: TextView = findViewById(R.id.textView6)
        val text7: TextView = findViewById(R.id.textView7)
        val text8: TextView = findViewById(R.id.textView8)
        val text9: TextView = findViewById(R.id.textView9)
        val one: String = "1"
        val two: String = "2"
        val three: String = "3"
        if (text1.text == one) {
            text1.text = ""
            text4.text = ""
            text7.text = ""
            text2.text = two
            text5.text = two
            text8.text = two
        }
        else if (text2.text == two) {
            text2.text = ""
            text5.text = ""
            text8.text = ""
            text3.text = three
            text6.text = three
            text9.text = three
        }
        else if (text3.text == three) {
            text3.text = ""
            text6.text = ""
            text9.text = ""
            text1.text = one
            text4.text = one
            text7.text = one
        }
    }
}
