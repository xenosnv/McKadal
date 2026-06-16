package com.example.mckadal

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        // 1. Hubungkan ID Teks Profil
        val tvUserName = findViewById<TextView>(R.id.tvUserName)
        val tvEmail = findViewById<TextView>(R.id.tvEmail)

        // 2. Hubungkan ID Tombol Login & Logout
        val btnGoToLogin = findViewById<LinearLayout>(R.id.btnGoToLogin)
        val btnLogout = findViewById<LinearLayout>(R.id.btnLogout)

        // Hubungkan ID Menu Lainnya
        val btnEditProfile = findViewById<LinearLayout>(R.id.btnEditProfile)
        val btnAddress = findViewById<LinearLayout>(R.id.btnAddress)

        // Hubungkan ID Bottom Navigation
        val navHome = findViewById<ImageView>(R.id.navHome)
        val navFav = findViewById<ImageView>(R.id.navFav)
        val navOrder = findViewById<ImageView>(R.id.navOrder)
        val navCart = findViewById<ImageView>(R.id.navCart)

        // --- CEK STATUS LOGIN ---
        val namaUser = intent.getStringExtra("NAMA")

        if (namaUser.isNullOrEmpty()) {
            tvUserName.text = "Guest"
            tvEmail.text = "Silakan login untuk menikmati fitur lengkap"
            btnGoToLogin.visibility = View.VISIBLE
            btnLogout.visibility = View.GONE
        } else {
            tvUserName.text = namaUser
            tvEmail.text = "user.mckadal@email.com"
            btnGoToLogin.visibility = View.GONE
            btnLogout.visibility = View.VISIBLE
        }

        // --- AKSI TOMBOL ---
        btnGoToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        btnLogout.setOnClickListener {
            Toast.makeText(this, "Berhasil Logout", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, KategoriActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }

        // --- NAVIGASI BOTTOM NAV (SEMUA KE KIRI) ---
        navHome.setOnClickListener {
            val intent = Intent(this, KategoriActivity::class.java)
            intent.putExtra("NAMA", namaUser)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            // Dari profil ke home = Mundur (Kiri)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }

        navFav.setOnClickListener {
            val intent = Intent(this, FavoriteActivity::class.java)
            intent.putExtra("NAMA", namaUser)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }

        navOrder.setOnClickListener {
            val intent = Intent(this, OrderActivity::class.java)
            intent.putExtra("NAMA", namaUser)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }

        navCart.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            intent.putExtra("NAMA", namaUser)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // Animasi mundur saat tekan tombol back device
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}