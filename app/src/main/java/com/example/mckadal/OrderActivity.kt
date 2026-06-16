package com.example.mckadal

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class OrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        val namaUser = intent.getStringExtra("NAMA") ?: ""

        // 1. Inisialisasi Komponen & Animasi Masuk
        val cardOngoing = findViewById<CardView>(R.id.cardOngoing)
        val slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up_fade)
        cardOngoing?.startAnimation(slideUp)

        // 2. Inisialisasi Navbar
        val navHome = findViewById<ImageView>(R.id.navHome)
        val navFav = findViewById<ImageView>(R.id.navFav)
        val navCart = findViewById<ImageView>(R.id.navCart)
        val navUser = findViewById<ImageView>(R.id.navUser)

        // --- LOGIKA NAVIGASI SLIDE ---

        // Pindah ke HOME (Mundur ke Kiri)
        navHome?.setOnClickListener {
            val intent = Intent(this, KategoriActivity::class.java)
            intent.putExtra("NAMA", namaUser)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }

        // Pindah ke FAVORITE (Mundur ke Kiri)
        navFav?.setOnClickListener {
            val intent = Intent(this, FavoriteActivity::class.java)
            intent.putExtra("NAMA", namaUser)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }

        // Pindah ke CART (Maju ke Kanan)
        navCart?.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            intent.putExtra("NAMA", namaUser)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        // Pindah ke USER (Maju ke Kanan)
        navUser?.setOnClickListener {
            val intent = Intent(this, UserActivity::class.java)
            intent.putExtra("NAMA", namaUser)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // Default back ke arah kanan
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}