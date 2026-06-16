package com.example.mckadal

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.google.android.material.button.MaterialButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMasuk = findViewById<MaterialButton>(R.id.btnMasuk)

        // Jalankan animasi awal
        try {
            val slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up_fade)
            btnMasuk.startAnimation(slideUp)
        } catch (e: Exception) { e.printStackTrace() }

        btnMasuk.setOnClickListener {
            try {
                val bounce = AnimationUtils.loadAnimation(this, R.anim.btn_bounce)
                btnMasuk.startAnimation(bounce)
            } catch (e: Exception) { e.printStackTrace() }

            btnMasuk.postDelayed({
                val intent = Intent(this, KategoriActivity::class.java)
                startActivity(intent)
                // GUNAKAN R.anim (milik projectmu), BUKAN android.R.anim
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }, 150)
        }
    }
}