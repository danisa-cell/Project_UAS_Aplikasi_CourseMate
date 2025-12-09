package com.example.projectuasaplikasikursusonline

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectuasaplikasikursusonline.storage.CourseProgressStorage

class FinishActivity : AppCompatActivity() {

    private val TAG = "FinishActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            setContentView(R.layout.activity_finish)

            val score = intent.getIntExtra("score", 0)
            val total = intent.getIntExtra("total", 0)
            val userAnswers = intent.getIntegerArrayListExtra("userAnswers") ?: arrayListOf()
            val questions = (intent.getSerializableExtra("questions") as? ArrayList<Quiz>) ?: arrayListOf()
            val courseId = intent.getStringExtra("courseId") ?: ""

            Log.d(TAG, "Received - Score: $score, Total: $total, courseId=$courseId")

            val tvScore = findViewById<TextView>(R.id.tvScore)
            val container = findViewById<LinearLayout>(R.id.containerAnswers)
            val btnBack = findViewById<Button>(R.id.btnBack)

            val percentage = if (total > 0) {
                (score.toDouble() / total * 100).toInt()
            } else 0

            // 🔥 UPDATE PROGRESS QUIZ
            if (courseId.isNotEmpty()) {
                CourseProgressStorage.updateQuizProgress(this, courseId, percentage)

                // ✅ Debug: Cek progress setelah disimpan
                val totalProgress = CourseProgressStorage.getTotalProgress(this, courseId)
                Log.d(TAG, "Quiz progress saved: $percentage%")
                Log.d(TAG, "Total progress now: $totalProgress%")

                Toast.makeText(this, "Progress tersimpan: $totalProgress%", Toast.LENGTH_SHORT).show()
            }

            tvScore.text = "Skor: $score / $total ($percentage%)"

            container.removeAllViews()

            if (questions.isEmpty()) {
                val tv = TextView(this)
                tv.text = "Tidak ada data soal untuk ditampilkan."
                tv.textSize = 16f
                tv.setTextColor(0xFFFFFFFF.toInt())
                tv.setPadding(20, 20, 20, 20)
                container.addView(tv)
            } else {
                for (i in questions.indices) {
                    val q = questions[i]
                    val userAns = userAnswers.getOrNull(i) ?: -1

                    val userText = if (userAns >= 0) q.options[userAns] else "Tidak dijawab"
                    val correctText = q.options[q.correctIndex]

                    val view = TextView(this)
                    view.text = """
                        • Soal ${i + 1}
                        ${q.question}

                        Jawaban Kamu  : $userText
                        Jawaban Benar : $correctText

                    """.trimIndent()

                    view.textSize = 14f
                    view.setPadding(20, 20, 20, 20)
                    view.setTextColor(0xFF000000.toInt())

                    view.setBackgroundColor(
                        if (userAns == q.correctIndex) 0xFFDFFFD6.toInt()
                        else 0xFFFFD6D6.toInt()
                    )

                    val params = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    params.setMargins(0, 10, 0, 10)
                    view.layoutParams = params

                    container.addView(view)
                }
            }

            btnBack.setOnClickListener {
                // ✅ PERBAIKAN: Kembali ke MainActivity (bukan HomeActivity)
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
                finish()
            }

        } catch (e: Exception) {
            Log.e(TAG, "Critical error in onCreate", e)
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}