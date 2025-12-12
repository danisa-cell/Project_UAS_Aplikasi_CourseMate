package com.example.projectuasaplikasikursusonline
// Menentukan paket/namespace tempat file ini berada.
// Penting untuk pengorganisasian project dan import class.

data class MycourseModel(               // Mendefinisikan data class untuk menyimpan data tiap course.
    val id: String,                     // id → identitas unik tiap course, berupa String, tidak bisa diubah (val).
    val title: String,                  // title → judul course yang ditampilkan di UI.
    val imageRes: Int,                  // imageRes → ID resource gambar (contoh: R.drawable.img1).
    var progress: Int = 0               // progress → nilai progres awal (default 0). Bisa berubah karena var.
    // Catatan: Progress yang dipakai di UI sebenarnya diambil dari CourseProgressStorage,
    // bukan dari nilai ini, sehingga parameter progress ini idealnya tidak dipakai lagi.
)
