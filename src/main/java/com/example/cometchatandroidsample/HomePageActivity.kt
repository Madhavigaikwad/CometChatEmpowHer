package com.example.cometchatandroidsample

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomePageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        // Health Section Button Click Listener
        findViewById<Button>(R.id.btn_health).setOnClickListener {
            startActivity(Intent(this, HealthActivity::class.java))
        }

        // Security Section Button Click Listener
        findViewById<Button>(R.id.btn_security).setOnClickListener {
            startActivity(Intent(this, SecurityActivity::class.java))
        }

        // Education Section Button Click Listener
        findViewById<Button>(R.id.btn_education).setOnClickListener {
            startActivity(Intent(this, EducationActivity::class.java))
        }

        // Chat Section Button Click Listener
        findViewById<Button>(R.id.btn_chat).setOnClickListener {
            startActivity(Intent(this, ConversationsActivity::class.java))
        }
    }
}
