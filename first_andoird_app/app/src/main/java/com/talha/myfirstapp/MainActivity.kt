package com.talha.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonClickMe = findViewById<Button>(R.id.myButton)
        val myTextView = findViewById<TextView>(R.id.textView)
        var timesClicked = 0
        buttonClickMe.setOnClickListener {
            timesClicked += 1
            myTextView.text = timesClicked.toString()
            Toast.makeText(this, "WHY????", Toast.LENGTH_SHORT).show()
        }
    }
}