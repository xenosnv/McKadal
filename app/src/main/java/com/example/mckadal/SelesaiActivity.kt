package com.example.mckadal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SelesaiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selesai)

        val btnBackHome = findViewById<Button>(R.id.btnBackHome)
        btnBackHome.setOnClickListener {
            // Balik ke Home (Kategori) dan hapus semua antrean halaman
            val intent = Intent(this, KategoriActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}