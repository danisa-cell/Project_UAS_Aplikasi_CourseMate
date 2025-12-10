package com.example.projectuasaplikasikursusonline.storage

import android.content.Context

object CourseProgressStorage {

    private const val PREF = "course_progress"

    // SIMPAN PROGRESS MATERI (selalu 50%)
    fun updateMaterialProgress(context: Context, courseId: String) {
        context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            .edit()
            .putInt("${courseId}_material", 50)
            .apply()
    }

    // SIMPAN PROGRESS QUIZ (0–100)
    fun updateQuizProgress(context: Context, courseId: String, progress: Int) {
        val safeQuiz = progress.coerceIn(0, 100)

        context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            .edit()
            .putInt("${courseId}_quiz", safeQuiz)
            .apply()
    }

    // TOTAL = 50 + quiz, dibatasi max 100
    fun getTotalProgress(context: Context, courseId: String): Int {
        val pref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)

        val material = pref.getInt("${courseId}_material", 0)
        val quiz = pref.getInt("${courseId}_quiz", 0)

        return (material + quiz).coerceAtMost(100)
    }

    // DIPAKAI MyCourseAdapter
    fun getProgress(context: Context, courseId: String): Int {
        return getTotalProgress(context, courseId)
    }

    fun resetCourseProgress(context: Context, courseId: String) {
        val prefs = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        prefs.edit()
            .remove("${courseId}_material")
            .remove("${courseId}_quiz")
            .apply()
    }

    fun resetAllProgress(context: Context) {
        val prefs = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        prefs.edit().clear().apply()
    }
}
