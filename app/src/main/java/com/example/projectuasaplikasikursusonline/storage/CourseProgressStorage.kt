package com.example.projectuasaplikasikursusonline.storage
// Package tempat class CourseProgressStorage disimpan

import android.content.Context
// Import Context untuk akses SharedPreferences

object CourseProgressStorage {
    // Membuat object (singleton) supaya bisa dipakai tanpa membuat instance

    private const val PREF = "course_progress"
    // Nama file SharedPreferences untuk menyimpan progress

    // SET MATERIAL PROGRESS FIX 50%
    fun updateMaterialProgress(context: Context, courseId: String) {
        // Function untuk mengatur progress materi, selalu 50%

        context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            // Ambil SharedPreferences dengan nama PREF

            .edit()
            // Masuk ke mode edit

            .putInt("${courseId}_material", 50)
            // Simpan nilai 50 ke key "courseId_material"

            .apply()
        // Terapkan perubahan secara async (lebih cepat)
    }

    // SIMPAN QUIZ PROGRESS (0–50)
    fun updateQuizProgress(context: Context, courseId: String, quizProgress: Int) {
        // Function untuk menyimpan progress quiz

        val safeQuiz = quizProgress.coerceIn(0, 50)
        // Pastikan nilai quiz hanya 0–50 (tidak boleh lebih)

        context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            // Ambil SharedPreferences

            .edit()
            // Mode edit

            .putInt("${courseId}_quiz", safeQuiz)
            // Simpan progress quiz pada key "courseId_quiz"

            .apply()
        // Terapkan perubahan
    }

    // HITUNG TOTAL PROGRESS (0–100)
    fun getTotalProgress(context: Context, courseId: String): Int {
        // Function untuk menghitung total progress (materi + quiz)

        val pref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        // Ambil SharedPreferences sekali agar lebih efisien

        val material = pref.getInt("${courseId}_material", 0)
        // Ambil progress materi (default 0 jika belum ada)

        val quiz = pref.getInt("${courseId}_quiz", 0)
        // Ambil progress quiz (default 0)

        return (material + quiz).coerceAtMost(100)
        // Jumlahkan materi + quiz, pastikan maksimal 100
    }

    // DIPAKAI ADAPTER
    fun getProgress(context: Context, courseId: String): Int {
        // Wrapper function, langsung kembalikan total progress

        return getTotalProgress(context, courseId)
    }

    fun resetCourseProgress(context: Context, courseId: String) {
        // Hapus progress satu course tertentu

        context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            // Ambil SharedPreferences

            .edit()
            // Mode edit

            .remove("${courseId}_material")
            // Hapus progress materi untuk course tersebut

            .remove("${courseId}_quiz")
            // Hapus progress quiz untuk course tersebut

            .apply()
        // Terapkan perubahan
    }

    fun resetAllProgress(context: Context) {
        // Hapus semua progress dari semua course

        context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            // Ambil SharedPreferences

            .edit()
            // Mode edit

            .clear()
            // Hapus semua data progress

            .apply()
        // Terapkan perubahan
    }
}
