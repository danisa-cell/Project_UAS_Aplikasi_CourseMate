package com.example.projectuasaplikasikursusonline

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.projectuasaplikasikursusonline.storage.CourseProgressStorage

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val score = intent.getIntExtra("score", 0)
        val total = intent.getIntExtra("total", 0)
        val courseId = intent.getStringExtra("courseId") ?: ""

        val questionList =
            intent.getSerializableExtra("questions") as ArrayList<Quiz>
        val userAnswers =
            intent.getIntegerArrayListExtra("userAnswers") ?: arrayListOf()

        val percentage = if (total > 0) (score * 100) / total else 0

        // ✅ Quiz progress dihitung dari skor (0-50%)
        val quizProgress50 =
            if (total > 0) ((score.toDouble() / total.toDouble()) * 50).toInt() else 0

        // ✅ Update quiz progress ke storage
        if (courseId.isNotEmpty()) {
            CourseProgressStorage.updateQuizProgress(this, courseId, quizProgress50)
        }

        val tvScore = findViewById<TextView>(R.id.tvScore)
        val container = findViewById<LinearLayout>(R.id.containerAnswers)
        val btnRetry = findViewById<Button>(R.id.btnRetry)
        val btnFinish = findViewById<Button>(R.id.btnFinish)

        tvScore.text = "Nilai Kamu: $percentage%\n($score dari $total soal)"

        container.removeAllViews()
        val limit = minOf(3, questionList.size)

        for (i in 0 until limit) {
            val q = questionList[i]
            val userIdx = userAnswers.getOrNull(i) ?: -1

            val tv = TextView(this)
            tv.text = """
                • Soal ${i + 1}
                ${q.question}

                Jawaban Kamu : ${if (userIdx >= 0) q.options[userIdx] else "—"}
                Jawaban Benar : ${q.options[q.correctIndex]}
            """.trimIndent()

            tv.textSize = 14f
            tv.setPadding(16, 16, 16, 16)
            tv.setTextColor(0xFF000000.toInt())
            tv.setBackgroundColor(
                if (userIdx == q.correctIndex) 0xFFDFFFD6.toInt() else 0xFFFFD6D6.toInt()
            )

            container.addView(tv)
        }

        btnRetry.setOnClickListener {
            val retry = Intent(this, QuizActivity::class.java)
            retry.putExtra("courseId", courseId)
            startActivity(retry)
            finish()
        }

        // ✅ FIX: Ganti nama variable 'finish' jadi 'finishIntent'
        //    agar tidak bentrok dengan function finish()
        btnFinish.setOnClickListener {
            val finishIntent = Intent(this, FinishActivity::class.java)
            finishIntent.putExtra("score", score)
            finishIntent.putExtra("total", total)
            finishIntent.putExtra("questions", questionList)
            finishIntent.putExtra("userAnswers", userAnswers)
            finishIntent.putExtra("courseId", courseId)
            startActivity(finishIntent)
            finish()  // ✅ Sekarang function finish() bisa dipanggil dengan benar
        }
    }
}