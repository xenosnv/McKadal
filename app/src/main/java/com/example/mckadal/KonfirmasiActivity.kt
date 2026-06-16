package com.example.mckadal

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class KonfirmasiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_konfirmasi)

        // 1. Ambil data dari Intent (Gabungan kode lamamu & alur Cart baru)
        val nama = intent.getStringExtra("NAMA") ?: ""
        val menu = intent.getStringExtra("MENU") ?: ""
        // Menggunakan TOTAL_BILL dari keranjang, tapi jika tidak ada, gunakan HARGA bawaanmu
        val totalBill = intent.getIntExtra("TOTAL_BILL", intent.getIntExtra("HARGA", 0))
        val qty = intent.getIntExtra("QUANTITY", 1)

        // 2. Hubungkan View dengan ID di XML Sukses yang baru
        val ivSuccess = findViewById<ImageView>(R.id.ivSuccess)
        val tvOrderMenu = findViewById<TextView>(R.id.tvOrderMenu)
        val tvOrderTotal = findViewById<TextView>(R.id.tvOrderTotal)
        val btnTrackOrder = findViewById<MaterialButton>(R.id.btnTrackOrder) // Mewarisi sifat btnKonfirmasi
        val btnHome = findViewById<MaterialButton>(R.id.btnHome) // Mewarisi sifat btnBatal

        // 3. Masukkan Data ke dalam View
        val namaMenu = menu.substringBefore(" -")
        tvOrderMenu.text = "Menu: 🦎 $namaMenu ($qty Items)"
        tvOrderTotal.text = "Total: Rp ${"%,d".format(totalBill).replace(',', '.')}"

        // 4. Jalankan Animasi Slide Up (Dari kode lamamu)
        val slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up_fade)
        ivSuccess.startAnimation(slideUp)
        tvOrderMenu.startAnimation(slideUp)
        tvOrderTotal.startAnimation(slideUp)
        btnTrackOrder.startAnimation(slideUp)
        btnHome.startAnimation(slideUp)

        // 5. Logika Tombol Lacak Pesanan (Mewarisi btnKonfirmasi lamamu)
        btnTrackOrder.setOnClickListener {
            val bounce = AnimationUtils.loadAnimation(this, R.anim.btn_bounce)
            btnTrackOrder.startAnimation(bounce)

            btnTrackOrder.postDelayed({
                // Saat ini hanya menampilkan pesan, karena kita belum buat Page 6
                // Jika kamu memutuskan membuat Halaman Tracking/Pembayaran sebagai Page 6,
                // masukkan kode Intent kamu di sini!
                Toast.makeText(this, "Pesanan atas nama $nama sedang disiapkan!", Toast.LENGTH_SHORT).show()

                /* Contoh jika mau dilanjut ke halaman selanjutnya:
                val intent = Intent(this, PembayaranActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                */
            }, 150)
        }

        // 6. Logika Tombol Home (Mewarisi btnBatal lamamu)
        btnHome.setOnClickListener {
            // Karena ini halaman sukses, batal/home akan melempar kembali ke Menu Utama
            val intent = Intent(this, KategoriActivity::class.java)
            intent.putExtra("NAMA", nama)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)

            @Suppress("DEPRECATION")
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out) // Animasi fade aslimu
            finish()
        }
    }
}