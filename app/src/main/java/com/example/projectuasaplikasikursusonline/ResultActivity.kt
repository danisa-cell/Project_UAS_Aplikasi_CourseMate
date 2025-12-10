package com.example.projectuasaplikasikursusonline

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.projectuasaplikasikursusonline.storage.CourseProgressStorage
import com.example.projectuasaplikasikursusonline.R

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)   // Menampilkan layout activity_result.xml

        // =============================
        // AMBIL DATA DARI INTENT
        // =============================
        val score = intent.getIntExtra("score", 0)          // Nilai benar
        val total = intent.getIntExtra("total", 0)          // Total soal
        val courseId = intent.getStringExtra("courseId") ?: ""  // ID course

        // List soal (objek Quiz) dikirim sebagai Serializable
        val questionList = intent.getSerializableExtra("questions") as ArrayList<Quiz>

        // Jawaban user dikirim sebagai ArrayList<Int>
        val userAnswers = intent.getIntegerArrayListExtra("userAnswers") ?: arrayListOf()

        // =============================
        // HITUNG PERSENTASE NILAI
        // =============================
        val percentage = if (total > 0) (score * 100) / total else 0

        // =============================
        // SIMPAN PROGRESS QUIZ KE STORAGE
        // =============================
        if (courseId.isNotEmpty()) {
            CourseProgressStorage.updateQuizProgress(this, courseId, percentage)
        }

        // =============================
        // INISIALISASI UI
        // =============================
        val tvScore = findViewById<TextView>(R.id.tvScore)                // Teks nilai
        val container = findViewById<LinearLayout>(R.id.containerAnswers) // Tempat preview jawaban
        val btnRetry = findViewById<Button>(R.id.btnRetry)                // Tombol ulangi
        val btnFinish = findViewById<Button>(R.id.btnFinish)              // Tombol selesai

        // Tampilkan nilai ke TextView
        tvScore.text = "Nilai Kamu: $percentage%\n($score dari $total soal)"

        // Bersihkan container agar tidak dobel
        container.removeAllViews()

        // Batasi preview hanya 3 soal pertama
        val previewLimit = minOf(3, questionList.size)

        // =============================
        // TAMPILKAN 3 SOAL PERTAMA
        // =============================
        for (i in 0 until previewLimit) {

            val q = questionList[i]                 // Soal ke-i
            val userIndex = userAnswers.getOrNull(i) ?: -1   // Jawaban user, default -1

            // Buat TextView baru untuk setiap soal
            val tv = TextView(this)

            tv.text = """
                • Soal ${i + 1}
                ${q.question}

                Jawaban Kamu : ${if (userIndex >= 0) q.options[userIndex] else "—"}
                Jawaban Benar : ${q.options[q.correctIndex]}
            """.trimIndent()

            tv.textSize = 14f                       // Ukuran teks
            tv.setPadding(16, 16, 16, 16)           // Padding agar rapi
            tv.setTextColor(android.graphics.Color.BLACK)

            // Warna latar: hijau bila benar, merah bila salah
            tv.setBackgroundColor(
                if (userIndex == q.correctIndex)
                    0xFFDFFFD6.toInt()     // hijau muda
                else
                    0xFFFFD6D6.toInt()     // merah muda
            )

            container.addView(tv)                    // Tambahkan ke container
        }

        // =============================
        // JIKA SOAL LEBIH DARI 3 → TAMBAH TEKS TAMBAHAN
        // =============================
        if (questionList.size > 3) {
            val tvMore = TextView(this)
            tvMore.text = "\n... dan ${questionList.size - 3} soal lainnya."
            tvMore.textSize = 14f
            tvMore.setPadding(16, 16, 16, 16)
            container.addView(tvMore)               // Tambahkan teks info
        }

        // =============================
        // TOMBOL RETRY (ULANG QUIZ)
        // =============================
        btnRetry.setOnClickListener {
            val retryIntent = Intent(this, QuizActivity::class.java)

            retryIntent.putExtra("courseId", courseId)   // Kirim courseId

            startActivity(retryIntent)  // Mulai ulang quiz
            finish()                    // Tutup halaman hasil
        }

        // =============================
        // TOMBOL FINISH (LANJUT KE FINISH SCREEN)
        // =============================
        btnFinish.setOnClickListener {
            val intent = Intent(this, FinishActivity::class.java)

            // Kirim data lengkap
            intent.putExtra("score", score)
            intent.putExtra("total", total)
            intent.putExtra("questions", questionList)
            intent.putExtra("userAnswers", userAnswers)
            intent.putExtra("courseId", courseId)

            startActivity(intent)   // Masuk halaman finish
            finish()
        }
    }
}
