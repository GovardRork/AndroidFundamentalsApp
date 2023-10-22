package com.example.androidfundamentalsapp

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val view = findViewById<FrameLayout>(R.id.fl_main)
        layoutInflater.inflate(R.layout.fragment_movie_list,view)
        val openButton:TextView = findViewById(R.id.tv_open_details)
        openButton.setOnClickListener{

        }
    }
}
