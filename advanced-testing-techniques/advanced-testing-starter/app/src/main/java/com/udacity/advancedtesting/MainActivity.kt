package com.udacity.advancedtesting

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = TextView(this)
        view.text = "Advanced Testing Demo"
        setContentView(view)
    }
}
