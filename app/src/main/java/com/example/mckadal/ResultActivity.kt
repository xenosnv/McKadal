package com.example.mckadal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val btnCheckStatus = findViewById<Button>(R.id.btnCheckStatus)
        btnCheckStatus.setOnClickListener {
            startActivity(Intent(this, SelesaiActivity::class.java))
        }
    }
}