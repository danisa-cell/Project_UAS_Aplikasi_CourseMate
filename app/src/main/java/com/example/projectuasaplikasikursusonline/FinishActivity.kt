package com.example.projectuasaplikasikursusonline
// Package utama tempat class FinishActivity berada

import android.content.Intent
// Untuk berpindah Activity

import android.os.Bundle
// Untuk state Activity saat dibuat

import android.util.Log
// Untuk menampilkan log debugging

import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
// UI components

import androidx.appcompat.app.AppCompatActivity
// Base class untuk Activity

import com.example.projectuasaplikasikursusonline.storage.CourseProgressStorage
// Import class penyimpanan progress

import com.example.projectuasaplikasikursusonline.R
// Import resource XML

class FinishActivity : AppCompatActivity() {

    private val TAG = "FinishActivity"
    // Tag untuk logging

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            setContentView(R.layout.activity_finish)
            // Set XML layout untuk halaman

            // =========================
            // AMBIL DATA DARI INTENT
            // =========================
            val score = intent.getIntExtra("score", 0)
            // Ambil skor quiz (default 0)

            val total = intent.getIntExtra("total", 0)
            // Ambil total soal (default 0)

            val userAnswers = intent.getIntegerArrayListExtra("userAnswers") ?: arrayListOf()
            // Ambil jawaban user (list index jawaban)

            val questions = (intent.getSerializableExtra("questions") as? ArrayList<Quiz>) ?: arrayListOf()
            // Ambil list soal lengkap, cast ke ArrayList<Quiz>

            val courseId = intent.getStringExtra("courseId") ?: ""
            // Ambil ID course untuk menyimpan progress

            Log.d(TAG, "Received - Score: $score, Total: $total, courseId='$courseId'")
            // Log untuk debugging

            // =========================
            // Bind view dari XML
            // =========================
            val tvScore = findViewById<TextView>(R.id.tvScore)
            // TextView untuk tampilkan skor

            val tvProgressInfo = findViewById<TextView>(R.id.tvProgressInfo)
            // TextView untuk tampil progress

            val container = findViewById<LinearLayout>(R.id.containerAnswers)
            // Container untuk menampilkan list jawaban

            val btnBack = findViewById<Button>(R.id.btnBack)
            // Tombol kembali ke Home

            // =========================
            // HITUNG PERSENTASE QUIZ
            // =========================
            val quizPercentage = if (total > 0) {
                (score.toDouble() / total * 100).toInt()
                // Hitung persen = skor / total * 100
            } else 0
            // Jika total soal 0, hasil = 0

            Log.d(TAG, "Quiz percentage: $quizPercentage%")

            // =========================
            // AMBIL PROGRESS MATERI (50%)
            // =========================
            val materialProgressOnly = 50
            // Sesuai aturan: materi selalu bernilai 50%

            // Ambil total progress terkini (materi 50 + quiz 0–50)
            val progressAfter = if (courseId.isNotEmpty()) {
                CourseProgressStorage.getTotalProgress(this, courseId)
            } else 0

            // Hitung progress quiz = total - 50 (materi)
            val quizProgressValue = progressAfter - materialProgressOnly

            Log.d(TAG, "Material Progress: $materialProgressOnly%")
            Log.d(TAG, "Quiz Progress: $quizProgressValue%")
            Log.d(TAG, "Total Progress After: $progressAfter%")

            // =========================
            // TAMPILKAN INFORMASI PROGRESS
            // =========================
            if (courseId.isNotEmpty()) {
                // Jika courseId valid → tampilkan progress

                tvProgressInfo.text = """
                    Progress Course
                  
                    Progress Sekarang: $progressAfter%
                    
                    Progress Bertambah: +$quizProgressValue%
                """.trimIndent()

                Toast.makeText(this, "✅ Progress bertambah +$quizProgressValue%!", Toast.LENGTH_LONG).show()

            } else {
                // Jika courseId kosong → error

                Log.e(TAG, "ERROR: courseId is empty! Progress not saved.")
                tvProgressInfo.text = "⚠ Error: courseId kosong!\nProgress tidak tersimpan."
                Toast.makeText(this, "Error: courseId kosong!", Toast.LENGTH_LONG).show()
            }

            // =========================
            // TAMPILKAN SKOR DI TEXTVIEW
            // =========================
            tvScore.text = "Skor: $score / $total ($quizPercentage%)"
            // Format skor quiz

            container.removeAllViews()
            // Bersihkan container jawaban

            // =========================
            // TAMPIL JAWABAN USER
            // =========================
            if (questions.isEmpty()) {
                // Jika tidak ada soal, tampilkan pesan kosong

                val tv = TextView(this)
                tv.text = "Tidak ada data soal untuk ditampilkan."
                tv.textSize = 16f
                tv.setTextColor(0xFFFFFFFF.toInt())
                tv.setPadding(20, 20, 20, 20)
                container.addView(tv)

            } else {
                // Loop semua soal

                for (i in questions.indices) {
                    val q = questions[i]
                    // Soal ke-i

                    val userAns = userAnswers.getOrNull(i) ?: -1
                    // Ambil jawaban user (default -1)

                    val userText = if (userAns >= 0) q.options[userAns] else "Tidak dijawab"
                    // Dapatkan text jawaban user

                    val correctText = q.options[q.correctIndex]
                    // Ambil jawaban benar

                    val view = TextView(this)
                    // Create text view per soal

                    view.text = """
                        • Soal ${i + 1}
                        ${q.question}

                        Jawaban Kamu  : $userText
                        Jawaban Benar : $correctText

                    """.trimIndent()
                    // Isi detail soal + jawaban

                    view.textSize = 14f
                    view.setPadding(20, 20, 20, 20)
                    view.setTextColor(0xFF000000.toInt())

                    view.setBackgroundColor(
                        if (userAns == q.correctIndex)
                            0xFFDFFFD6.toInt()  // Hijau jika benar
                        else
                            0xFFFFD6D6.toInt()  // Merah jika salah
                    )

                    // Atur margin tiap item
                    val params = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    params.setMargins(0, 10, 0, 10)
                    view.layoutParams = params

                    container.addView(view)
                    // Tambahkan ke container
                }
            }

            // =========================
            // TOMBOL BACK
            // =========================
            btnBack.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                // Pindah ke MainActivity

                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                // Hindari membuka banyak activity bertumpuk

                startActivity(intent)
                finish()
            }

        } catch (e: Exception) {
            // Jika terjadi error fatal di onCreate

            Log.e(TAG, "Critical error in onCreate", e)
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}
