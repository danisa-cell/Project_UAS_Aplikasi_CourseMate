package com.example.projectuasaplikasikursusonline
// → Menentukan bahwa file ini berada dalam package projectuasaplikasikursusonline

data class Course(
    val title: String,       // Judul course, contoh: "Android Development"
    val price: String,       // Harga course, contoh: "Rp 250.000"
    val imageRes: Int,       // ID drawable untuk icon/gambar course
    val tutorName: String,   // Nama tutor, contoh: "Budi Santoso"
    val tutorImage: Int,     // ID drawable untuk foto tutor
    val description: String  // Deskripsi lengkap tentang isi course
)
// → data class digunakan untuk menyimpan data model course.
//   Secara otomatis menyediakan equals(), toString(), hashCode(), dan copy().
//   Class ini dipakai di HomeFragment & CourseAdapter.
