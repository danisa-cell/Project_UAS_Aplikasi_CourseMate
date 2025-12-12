package com.example.projectuasaplikasikursusonline.storage
// Package tempat class CourseProgress disimpan

data class CourseProgress(
    val courseId: String,   // ID unik untuk setiap course (bersifat tetap / tidak bisa diubah)
    var progress: Int       // Nilai progress course (0–100), bersifat mutable karena bisa berubah
)
