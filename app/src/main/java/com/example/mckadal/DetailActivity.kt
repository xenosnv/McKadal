package com.example.mckadal

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.button.MaterialButton

class DetailActivity : AppCompatActivity() {

    // Variabel untuk menyimpan jumlah pesanan dan status favorit
    private var quantity = 1
    private var basePrice = 0
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail) // Pastikan sesuai nama file XML barumu

        // 1. Ambil data dari Intent sebelumnya
        val nama = intent.getStringExtra("NAMA") ?: ""
        val kategori = intent.getStringExtra("KATEGORI") ?: ""
        val menu = intent.getStringExtra("MENU") ?: ""
        basePrice = intent.getIntExtra("HARGA", 0)

        // 2. Kenalkan View dengan ID dari XML Baru
        val tvDetailTitle = findViewById<TextView>(R.id.tvDetailTitle)
        val tvDetailDesc = findViewById<TextView>(R.id.tvDetailDesc)
        val tvDetailPrice = findViewById<TextView>(R.id.tvDetailPrice)

        val btnBack = findViewById<CardView>(R.id.btnBack)
        val btnFavDetail = findViewById<CardView>(R.id.btnFavDetail)
        val ivFavDetail = findViewById<ImageView>(R.id.ivFavDetail)

        val btnMinus = findViewById<CardView>(R.id.btnMinus)
        val btnPlus = findViewById<CardView>(R.id.btnPlus)
        val tvQuantity = findViewById<TextView>(R.id.tvQuantity)
        val btnAddCart = findViewById<MaterialButton>(R.id.btnAddCart)

        // 3. Masukkan Data ke dalam View
        tvDetailTitle.text = menu.substringBefore(" -")
        tvDetailPrice.text = formatRupiah(basePrice)

        val (deskripsi, kalori) = getDeskripsiMenu(menu)
        // Karena di desain baru tidak ada TextView khusus kalori, kita gabung ke dalam deskripsi
        tvDetailDesc.text = "$deskripsi\n\n🔥 $kalori kkal"

        updateCartUI(tvQuantity, btnAddCart) // Set harga awal di tombol

        // 4. Jalankan Animasi (dari kode lama kamu)
        val slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up_fade)
        tvDetailTitle.startAnimation(slideUp)
        tvDetailDesc.startAnimation(slideUp)
        btnAddCart.startAnimation(slideUp)

        // 5. Fungsi Tombol Back (Kembali)
        btnBack.setOnClickListener {
            finish() // Menutup halaman dan kembali ke halaman Home
        }

        // 6. Fungsi Tombol Favorit
        btnFavDetail.setOnClickListener {
            isFavorite = !isFavorite
            if (isFavorite) {
                ivFavDetail.setImageResource(android.R.drawable.btn_star_big_on)
                ivFavDetail.setColorFilter(Color.parseColor("#FF4B4B")) // Ubah jadi Merah
            } else {
                ivFavDetail.setImageResource(android.R.drawable.btn_star_big_off)
                ivFavDetail.setColorFilter(Color.parseColor("#DDDDDD")) // Ubah jadi Abu-abu
            }
        }

        // 7. Fungsi Pengatur Jumlah Pesanan (+ dan -)
        btnMinus.setOnClickListener {
            if (quantity > 1) {
                quantity--
                updateCartUI(tvQuantity, btnAddCart)
            }
        }

        btnPlus.setOnClickListener {
            quantity++
            updateCartUI(tvQuantity, btnAddCart)
        }

        // 8. Fungsi Tombol Add to Cart (Digabung dengan Animasi & Intent lama kamu)
        btnAddCart.setOnClickListener {
            val bounce = AnimationUtils.loadAnimation(this, R.anim.btn_bounce)
            btnAddCart.startAnimation(bounce)

            btnAddCart.postDelayed({
                val intent = Intent(this, CartActivity::class.java)
                intent.putExtra("NAMA", nama)
                intent.putExtra("KATEGORI", kategori)
                intent.putExtra("MENU", menu)
                intent.putExtra("HARGA", basePrice)
                // Tambahan: Mengirim total pesanan & total harga ke keranjang!
                intent.putExtra("QUANTITY", quantity)
                intent.putExtra("TOTAL_HARGA", basePrice * quantity)

                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
            }, 150)
        }
    }

    // Fungsi tambahan untuk mengupdate jumlah dan harga di tombol Add to Cart
    private fun updateCartUI(tvQty: TextView, btnCart: MaterialButton) {
        tvQty.text = quantity.toString()
        val totalPrice = basePrice * quantity
        btnCart.text = "Add to Cart - ${formatRupiah(totalPrice)}"
    }

    // Fungsi format Rupiah agar lebih rapi dipanggil berulang
    private fun formatRupiah(nominal: Int): String {
        return "Rp ${"%,d".format(nominal).replace(',', '.')}"
    }

    // Fungsi aslimu (tidak ada yang diubah)
    private fun getDeskripsiMenu(menu: String): Pair<String, Int> {
        return when {
            menu.contains("Burger Kadal") -> Pair(
                "Burger spesial McKadal dengan patty daging sapi pilihan, selada segar, tomat, dan saus kadal rahasia kami yang legendaris. Disajikan dengan roti brioche premium.", 520)
            menu.contains("Nasi Goreng Alien") -> Pair(
                "Nasi goreng dengan bumbu rempah alien dari luar angkasa 👽 Perpaduan rasa gurih, pedas, dan sedikit aneh yang bikin ketagihan!", 480)
            menu.contains("McWrap Lumpur") -> Pair(
                "Wrap tortilla isi ayam crispy, selada, keju, dan saus lumpur spesial McKadal. Praktis dimakan kapan aja!", 420)
            menu.contains("Es Teh Lumpur") -> Pair(
                "Teh manis dingin dengan sedikit sentuhan lumpur McKadal yang misterius. Segar dan bikin nagih!", 150)
            menu.contains("Jus Kadal") -> Pair(
                "Jus buah segar dengan resep rahasia kadal, kaya vitamin dan mineral. Dijamin menyegarkan!", 180)
            menu.contains("McShake") -> Pair(
                "Milkshake coklat creamy lembut dengan topping whipped cream dan taburan coklat. Surga dalam segelas!", 350)
            menu.contains("Paket Hemat") -> Pair(
                "1 Burger Kadal + 1 Es Teh Lumpur + 1 McFries Kadal. Hemat tapi tetap kenyang dan puas!", 700)
            menu.contains("Paket Keluarga") -> Pair(
                "4 Burger Kadal + 4 Es Teh Lumpur + 2 Large McFries. Cocok untuk makan bersama keluarga!", 2800)
            menu.contains("McDouble") -> Pair(
                "2 Burger Kadal + 2 Minuman pilihan. Double senangnya, double kenyangnya!", 1040)
            else -> Pair("Menu spesial McKadal yang lezat dan menggugah selera!", 400)
        }
    }
}