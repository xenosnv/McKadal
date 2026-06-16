package com.example.mckadal

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class FavoriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        val namaUser = intent.getStringExtra("NAMA") ?: ""

        // --- 1. INISIALISASI KOMPONEN ---
        val cardFav1 = findViewById<CardView>(R.id.cardFav1)
        val cardFav2 = findViewById<CardView>(R.id.cardFav2)
        val btnBackFav = findViewById<CardView>(R.id.btnBackFav)

        val btnRemoveFav1 = findViewById<ImageView>(R.id.btnRemoveFav1)
        val btnRemoveFav2 = findViewById<ImageView>(R.id.btnRemoveFav2)

        // Animasi halus saat item muncul (opsional tetap pakai slide up fade biar cakep)
        val slideUpFade = AnimationUtils.loadAnimation(this, R.anim.slide_up_fade)
        cardFav1?.startAnimation(slideUpFade)
        cardFav2?.postDelayed({ cardFav2?.startAnimation(slideUpFade) }, 150)


        // --- 2. LOGIKA REMOVE FAVORITE (LEPAS BINTANG) ---

        btnRemoveFav1?.setOnClickListener {
            cardFav1?.visibility = View.GONE
            Toast.makeText(this, "Burger Kadal Matcha dihapus", Toast.LENGTH_SHORT).show()
        }

        btnRemoveFav2?.setOnClickListener {
            cardFav2?.visibility = View.GONE
            Toast.makeText(this, "Es Teh dihapus", Toast.LENGTH_SHORT).show()
        }


        // --- 3. TOMBOL BACK (SLIDE KE KANAN) ---
        btnBackFav?.setOnClickListener {
            finish()
            // Halaman Favorite keluar ke kanan, halaman sebelumnya muncul dari kiri
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }


        // --- 4. NAVIGASI BOTTOM BAR (SLIDE KIRI-KANAN) ---
        val navHome = findViewById<ImageView>(R.id.navHome)
        val navOrder = findViewById<ImageView>(R.id.navOrder)
        val navCart = findViewById<ImageView>(R.id.navCart)
        val navUser = findViewById<ImageView>(R.id.navUser)

        // Balik ke Home (Slide ke Kiri)
        navHome?.setOnClickListener {
            val intent = Intent(this, KategoriActivity::class.java)
            intent.putExtra("NAMA", namaUser)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
            finish()
        }

        // Ke Cart (Slide ke Kanan)
        navCart?.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            intent.putExtra("NAMA", namaUser)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        // Ke Order (Slide ke Kanan)
        navOrder?.setOnClickListener {
            val intent = Intent(this, OrderActivity::class.java)
            intent.putExtra("NAMA", namaUser)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        // Ke User (Slide ke Kanan)
        navUser?.setOnClickListener {
            val intent = Intent(this, UserActivity::class.java)
            intent.putExtra("NAMA", namaUser)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }

    // Handle tombol back fisik agar tetap slide ke kanan
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}