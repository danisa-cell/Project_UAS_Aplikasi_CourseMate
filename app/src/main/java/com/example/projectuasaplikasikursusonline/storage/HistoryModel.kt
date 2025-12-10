package com.example.projectuasaplikasikursusonline.storage

// Data class sebagai model untuk setiap item riwayat
data class HistoryModel(
    var title: String,   // Judul transaksi / kursus
    var date: String,    // Tanggal transaksi
    var status: String,  // Status transaksi (Berhasil, Menunggu, Kadaluarsa, dll)
    var image: Int       // Gambar ikon history (drawable)
)
