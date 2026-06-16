package com.example.mckadal

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.button.MaterialButton

class CartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        // 1. Ambil data dari Intent
        val nama = intent.getStringExtra("NAMA") ?: ""
        val menu = intent.getStringExtra("MENU") ?: ""
        val hargaSatuan = intent.getIntExtra("HARGA", 0)
        val qty = intent.getIntExtra("QUANTITY", 1)
        val subtotal = intent.getIntExtra("TOTAL_HARGA", hargaSatuan)
        val deliveryFee = 10000
        val totalBill = subtotal + deliveryFee

        // 2. Hubungkan View (Data Keranjang)
        val tvCartMenuName = findViewById<TextView>(R.id.tvCartMenuName)
        val tvCartQty = findViewById<TextView>(R.id.tvCartQty)
        val tvCartPrice = findViewById<TextView>(R.id.tvCartPrice)
        val tvSubtotal = findViewById<TextView>(R.id.tvSubtotal)
        val tvTotalBill = findViewById<TextView>(R.id.tvTotalBill)
        val btnBackCart = findViewById<CardView>(R.id.btnBackCart)
        val btnOrder = findViewById<MaterialButton>(R.id.btnOrder)

        // 3. Hubungkan View (Navbar)
        val navHome = findViewById<ImageView>(R.id.navHome)
        val navFav = findViewById<ImageView>(R.id.navFav)
        val navOrder = findViewById<ImageView>(R.id.navOrder)
        val navUser = findViewById<ImageView>(R.id.navUser)

        // 4. Masukkan Data ke View
        val namaMenu = menu.substringBefore(" -")
        tvCartMenuName.text = "🦎 $namaMenu"
        tvCartQty.text = "$qty Items"
        tvCartPrice.text = formatRupiah(subtotal)
        tvSubtotal.text = formatRupiah(subtotal)
        tvTotalBill.text = formatRupiah(totalBill)

        // 5. Animasi Masuk
        val slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up_fade)
        tvCartMenuName.startAnimation(slideUp)
        btnOrder.startAnimation(slideUp)

        // --- LOGIKA TOMBOL PROSES ---

        btnOrder.setOnClickListener {
            val bounce = AnimationUtils.loadAnimation(this, R.anim.btn_bounce)
            btnOrder.startAnimation(bounce)
            btnOrder.postDelayed({
                val intent = Intent(this, KonfirmasiActivity::class.java)
                intent.putExtra("NAMA", nama)
                intent.putExtra("MENU", menu)
                intent.putExtra("TOTAL_BILL", totalBill)
                startActivity(intent)
                // Maju ke Konfirmasi (Kanan)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }, 150)
        }

        btnBackCart.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }

        // --- LOGIKA NAVBAR (ANIMASI SLIDE) ---

        navHome?.setOnClickListener {
            val intent = Intent(this, KategoriActivity::class.java)
            intent.putExtra("NAMA", nama)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }

        navFav?.setOnClickListener {
            val intent = Intent(this, FavoriteActivity::class.java)
            intent.putExtra("NAMA", nama)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }

        navOrder?.setOnClickListener {
            val intent = Intent(this, OrderActivity::class.java)
            intent.putExtra("NAMA", nama)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }

        navUser?.setOnClickListener {
            val intent = Intent(this, UserActivity::class.java)
            intent.putExtra("NAMA", nama)
            startActivity(intent)
            // Ke User (Maju ke Kanan)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }

    private fun formatRupiah(nominal: Int): String {
        return "Rp ${"%,d".format(nominal).replace(',', '.')}"
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}