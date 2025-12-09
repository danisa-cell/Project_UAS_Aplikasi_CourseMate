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
        setContentView(R.layout.activity_result)

        val score = intent.getIntExtra("score", 0)
        val total = intent.getIntExtra("total", 0)

        val courseId = intent.getStringExtra("courseId") ?: ""

        val questionList = intent.getSerializableExtra("questions") as ArrayList<Quiz>
        val userAnswers = intent.getIntegerArrayListExtra("userAnswers") ?: arrayListOf()

// ResultActivity.kt - Bagian onCreate()
        val percentage = if (total > 0) (score * 100) / total else 0
        if (courseId.isNotEmpty()) {
            val courseId = intent.getStringExtra("courseId") ?: ""
            CourseProgressStorage.updateQuizProgress(this, courseId, percentage)
       }

        val tvScore = findViewById<TextView>(R.id.tvScore)
        val container = findViewById<LinearLayout>(R.id.containerAnswers)
        val btnRetry = findViewById<Button>(R.id.btnRetry)
        val btnFinish = findViewById<Button>(R.id.btnFinish)

        tvScore.text = "Nilai Kamu: $percentage%\n($score dari $total soal)"

        container.removeAllViews()
        val previewLimit = minOf(3, questionList.size)

        for (i in 0 until previewLimit) {

            val q = questionList[i]
            val userIndex = userAnswers.getOrNull(i) ?: -1

            val tv = TextView(this)
            tv.text = """
                • Soal ${i + 1}
                ${q.question}

                Jawaban Kamu : ${if (userIndex >= 0) q.options[userIndex] else "—"}
                Jawaban Benar : ${q.options[q.correctIndex]}
            """.trimIndent()

            tv.textSize = 14f
            tv.setPadding(16, 16, 16, 16)
            tv.setTextColor(android.graphics.Color.BLACK)
            tv.setBackgroundColor(
                if (userIndex == q.correctIndex) 0xFFDFFFD6.toInt()
                else 0xFFFFD6D6.toInt()
            )

            container.addView(tv)
        }

        if (questionList.size > 3) {
            val tvMore = TextView(this)
            tvMore.text = "\n... dan ${questionList.size - 3} soal lainnya."
            tvMore.textSize = 14f
            tvMore.setPadding(16, 16, 16, 16)
            container.addView(tvMore)
        }

        btnRetry.setOnClickListener {
            val retryIntent = Intent(this, QuizActivity::class.java)
            retryIntent.putExtra("courseId", courseId)
            startActivity(retryIntent)
            finish()
        }

        // ✅ FIX TERPENTING — KIRIM SEMUA DATA KE FinishActivity
        btnFinish.setOnClickListener {

            val intent = Intent(this, FinishActivity::class.java)

            // KIRIM DATA LENGKAP
            intent.putExtra("score", score)
            intent.putExtra("total", total)
            intent.putExtra("questions", questionList)
            intent.putExtra("userAnswers", userAnswers)
            intent.putExtra("courseId", courseId)

            startActivity(intent)
            finish()
        }
    }
}
