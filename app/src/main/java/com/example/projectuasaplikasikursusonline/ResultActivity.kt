package com.example.projectuasaplikasikursusonline

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val score = intent.getIntExtra("score", 0)
        val totalQuestions = QuizData.questions.size
        val finalScore = (score.toDouble() / totalQuestions.toDouble()) * 100

        val tvScore = findViewById<TextView>(R.id.tvScore)
        val tvDetail = findViewById<TextView>(R.id.tvDetail)
        val btnFinish = findViewById<Button>(R.id.btnFinish)

        tvScore.text = "Nilai Kamu: ${finalScore.toInt()}"
        tvDetail.text = """
            Jawaban Benar : $score
            Jawaban Salah : ${totalQuestions - score}
            Total Soal    : $totalQuestions
        """.trimIndent()

        // ====== FINISH BUTTON ======
        btnFinish.setOnClickListener {
            val intent = Intent(this, FinishActivity::class.java)
            startActivity(intent)
            finish() // biar ga bisa balik ke result
        }
    }
}
