package com.example.mckadal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PembayaranActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembayaran)

        // Tombol buat lanjut ke Result
        val btnBayar = findViewById<Button>(R.id.btnBayar)
        btnBayar.setOnClickListener {
            startActivity(Intent(this, ResultActivity::class.java))
        }
    }
}