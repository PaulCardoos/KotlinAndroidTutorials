package com.example.findmyage

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edit = findViewById<EditText>(R.id.enter_age_text)
        val text = findViewById<TextView>(R.id.age_display)
        val button = findViewById<Button>(R.id.find_age_btn)
        button.setOnClickListener {
            var number = edit.text.toString()
            number = (2021 - number.toInt()).toString()
            text.text = "You are $number"
        }
    }
}


