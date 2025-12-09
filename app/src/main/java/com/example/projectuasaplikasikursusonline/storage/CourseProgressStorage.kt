package com.example.projectuasaplikasikursusonline.storage

import android.content.Context

object CourseProgressStorage {

    private const val PREF = "course_progress"

    // SIMPAN PROGRESS MATERI
    fun updateMaterialProgress(context: Context, courseId: String, progress: Int) {
        context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            .edit()
            .putInt("${courseId}_material", progress)
            .apply()
    }

    // SIMPAN PROGRESS QUIZ
    fun updateQuizProgress(context: Context, courseId: String, progress: Int) {
        context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            .edit()
            .putInt("${courseId}_quiz", progress)
            .apply()
    }

    // AMBIL TOTAL PROGRESS (RATA2)
    fun getTotalProgress(context: Context, courseId: String): Int {
        val pref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)

        val material = pref.getInt("${courseId}_material", 0)
        val quiz = pref.getInt("${courseId}_quiz", 0)

        return (material + quiz) / 2
    }

    // 🔥 DIPAKAI MyCourseAdapter → WAJIB ADA
    fun getProgress(context: Context, courseId: String): Int {
        return getTotalProgress(context, courseId)
    }
}
