package com.udacity.unittesting

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = TextView(this)
        view.text = "Unit Testing Exercise"
        setContentView(view)
    }
}
