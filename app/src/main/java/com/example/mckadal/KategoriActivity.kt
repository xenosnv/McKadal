package com.example.mckadal

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class KategoriActivity : AppCompatActivity() {

    // Status favorit untuk 4 makanan
    private var isFavFood1 = true
    private var isFavFood2 = false
    private var isFavFood3 = true // Burger Matcha default favorit
    private var isFavFood4 = true // Es Teh default favorit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kategori)

        val namaUser = intent.getStringExtra("NAMA") ?: ""

        // --- 1. INISIALISASI ID MAKANAN ---
        val notifDot = findViewById<CardView>(R.id.notifDot)

        // Ikon Heart/Bintang
        val ivHeart1 = findViewById<ImageView>(R.id.ivHeart1)
        val ivHeart2 = findViewById<ImageView>(R.id.ivHeart2)
        val ivHeart3 = findViewById<ImageView>(R.id.ivHeart3) // New
        val ivHeart4 = findViewById<ImageView>(R.id.ivHeart4) // New

        // Gambar Makanan untuk Detail
        val ivFood1 = findViewById<ImageView>(R.id.ivFood1)
        val ivFood2 = findViewById<ImageView>(R.id.ivFood2)
        val ivFood3 = findViewById<ImageView>(R.id.ivFood3) // New
        val ivFood4 = findViewById<ImageView>(R.id.ivFood4) // New

        // Tombol Add to Cart
        val btnAdd1 = findViewById<CardView>(R.id.btnAdd1)
        val btnAdd2 = findViewById<CardView>(R.id.btnAdd2)
        val btnAdd3 = findViewById<CardView>(R.id.btnAdd3) // New
        val btnAdd4 = findViewById<CardView>(R.id.btnAdd4) // New

        // --- 2. LOGIKA FAVORIT (TOGGLE) ---
        fun updateHeart(view: ImageView?, isFav: Boolean) {
            view?.setImageResource(if (isFav) android.R.drawable.btn_star_big_on else android.R.drawable.btn_star_big_off)
            view?.setColorFilter(Color.parseColor(if (isFav) "#FF4B4B" else "#DDDDDD"))
        }

        ivHeart1?.setOnClickListener { isFavFood1 = !isFavFood1; updateHeart(ivHeart1, isFavFood1) }
        ivHeart2?.setOnClickListener { isFavFood2 = !isFavFood2; updateHeart(ivHeart2, isFavFood2) }
        ivHeart3?.setOnClickListener { isFavFood3 = !isFavFood3; updateHeart(ivHeart3, isFavFood3) }
        ivHeart4?.setOnClickListener { isFavFood4 = !isFavFood4; updateHeart(ivHeart4, isFavFood4) }

        // --- 3. LOGIKA KERANJANG ---
        val addToCart = View.OnClickListener {
            notifDot?.visibility = View.VISIBLE
            Toast.makeText(this, "Berhasil masuk ke keranjang 🛒", Toast.LENGTH_SHORT).show()
        }
        btnAdd1?.setOnClickListener(addToCart)
        btnAdd2?.setOnClickListener(addToCart)
        btnAdd3?.setOnClickListener(addToCart)
        btnAdd4?.setOnClickListener(addToCart)

        // --- 4. NAVIGASI DETAIL ---
        fun openDetail(menuName: String) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("NAMA", namaUser)
            intent.putExtra("MENU", menuName)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        ivFood1?.setOnClickListener { openDetail("Golden Spicy Chicken") }
        ivFood2?.setOnClickListener { openDetail("Cheese Burger Nagi") }
        ivFood3?.setOnClickListener { openDetail("Burger Kadal Matcha") }
        ivFood4?.setOnClickListener { openDetail("Es Teh Segar") }

        // --- 5. NAVBAR NAVIGATION ---
        val navFav = findViewById<ImageView>(R.id.navFav)
        val navCart = findViewById<ImageView>(R.id.navCart)
        val navOrder = findViewById<ImageView>(R.id.navOrder)
        val navUser = findViewById<ImageView>(R.id.navUser)

        navFav?.setOnClickListener {
            startActivity(Intent(this, FavoriteActivity::class.java).putExtra("NAMA", namaUser))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        navCart?.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java).putExtra("NAMA", namaUser))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        navOrder?.setOnClickListener {
            startActivity(Intent(this, OrderActivity::class.java).putExtra("NAMA", namaUser))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        navUser?.setOnClickListener {
            startActivity(Intent(this, UserActivity::class.java).putExtra("NAMA", namaUser))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }
}