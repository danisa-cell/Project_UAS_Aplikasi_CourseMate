package com.example.projectuasaplikasikursusonline

data class MycourseModel(
    val id: String,
    val title: String,
    val imageRes: Int,
    var progress: Int = 0
    // ✅ HAPUS parameter progress (biar diambil dari storage)
)