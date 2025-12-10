package com.example.projectuasaplikasikursusonline
// Menentukan package tempat file Kotlin ini berada.
// Package digunakan untuk mengelompokkan file berdasarkan modul atau fitur aplikasi.

import android.os.Bundle
// Mengimpor class Bundle, digunakan untuk menyimpan dan memulihkan data saat Activity dibuat.

import androidx.appcompat.app.AppCompatActivity
// Mengimpor AppCompatActivity, yaitu class dasar untuk membuat Activity modern di Android.

import com.example.projectuasaplikasikursusonline.R
// Mengimpor file R yang berisi seluruh resource aplikasi (layout, ID elemen, gambar, warna, dll).

class FinishScreenActivity : AppCompatActivity() {
    // Mendeklarasikan class FinishScreenActivity.
    // Activity ini adalah sebuah layar baru yang muncul di aplikasi.
    // Tanda ": AppCompatActivity()" berarti class ini mewarisi (inherit) dari AppCompatActivity.

    override fun onCreate(savedInstanceState: Bundle?) {
        // Fungsi onCreate dipanggil pertama kali ketika Activity ini dibuka.
        // Parameter savedInstanceState menyimpan data ketika Activity di-restart
        // (biasanya null saat pertama kali dibuka).

        super.onCreate(savedInstanceState)
        // Memanggil implementasi onCreate milik AppCompatActivity.
        // WAJIB dilakukan agar proses dasar Activity dapat berjalan (membuat tampilan, window, dll).

        setContentView(R.layout.activity_finish)
        // Mengatur tampilan Activity menggunakan file layout activity_finish.xml.
        // Semua elemen UI di XML tersebut akan menjadi tampilan layar Activity ini.
    }
}
