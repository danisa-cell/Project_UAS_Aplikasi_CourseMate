package com.example.projectuasaplikasikursusonline

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FinishActivity : AppCompatActivity() {

    private val TAG = "FinishActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            setContentView(R.layout.activity_finish)

            // Ambil data dari intent
            val score = intent.getIntExtra("score", 0)
            val total = intent.getIntExtra("total", 0)
            val userAnswers = intent.getIntegerArrayListExtra("userAnswers") ?: arrayListOf()
            val questions = (intent.getSerializableExtra("questions") as? ArrayList<Quiz>) ?: arrayListOf()

            Log.d(TAG, "Received - Score: $score, Total: $total, Questions: ${questions.size}, Answers: ${userAnswers.size}")

            // Ambil views
            val tvScore = findViewById<TextView>(R.id.tvScore)
            val container = findViewById<LinearLayout>(R.id.containerAnswers)
            val btnBack = findViewById<Button>(R.id.btnBack)

            // Hitung persentase
            val percentage = if (total > 0) {
                (score.toDouble() / total * 100).toInt()
            } else {
                0
            }

            // Set score text
            tvScore.text = "Skor: $score / $total ($percentage%)"

            // Clear container
            container.removeAllViews()

            // Validasi data
            if (questions.isEmpty()) {
                val tv = TextView(this)
                tv.text = "Tidak ada data soal untuk ditampilkan."
                tv.textSize = 16f
                tv.setTextColor(0xFFFFFFFF.toInt())
                tv.setPadding(20, 20, 20, 20)
                container.addView(tv)
                Log.w(TAG, "Questions list is empty")
            } else {
                // Generate SEMUA jawaban (15 soal lengkap)
                for (i in questions.indices) {
                    try {
                        val q = questions[i]
                        val userAns = if (i < userAnswers.size) userAnswers[i] else -1
                        val correctAns = q.correctIndex

                        val userAnswerText = if (userAns >= 0 && userAns < q.options.size) {
                            q.options[userAns]
                        } else {
                            "Tidak dijawab"
                        }

                        val correctAnswerText = if (correctAns >= 0 && correctAns < q.options.size) {
                            q.options[correctAns]
                        } else {
                            "—"
                        }

                        val view = TextView(this)
                        view.text = """
                            • Soal ${i + 1}
                            ${q.question}

                            Jawaban Kamu     : $userAnswerText
                            Jawaban Benar    : $correctAnswerText

                        """.trimIndent()

                        view.textSize = 14f
                        view.setPadding(20, 20, 20, 20)
                        view.setTextColor(0xFF000000.toInt())

                        // Warna hijau jika benar, merah jika salah
                        if (userAns == correctAns) {
                            view.setBackgroundColor(0xFFDFFFD6.toInt()) // hijau
                        } else {
                            view.setBackgroundColor(0xFFFFD6D6.toInt()) // merah
                        }

                        val params = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )
                        params.setMargins(0, 10, 0, 10)
                        view.layoutParams = params

                        container.addView(view)

                    } catch (e: Exception) {
                        Log.e(TAG, "Error adding question $i", e)
                    }
                }

                Log.d(TAG, "Successfully added ${questions.size} questions to container")
            }

            // Button kembali - GANTI KE HomeActivity
            btnBack.setOnClickListener {
                try {
                    Log.d(TAG, "Going back to HomeActivity")
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()
                } catch (e: Exception) {
                    Log.e(TAG, "Error going to HomeActivity", e)
                    Toast.makeText(this, "Gagal kembali ke menu", Toast.LENGTH_SHORT).show()
                }
            }

        } catch (e: Exception) {
            Log.e(TAG, "Critical error in onCreate", e)
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}