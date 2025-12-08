package com.example.projectuasaplikasikursusonline

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    private val TAG = "ResultActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "========================================")
        Log.d(TAG, "=== ResultActivity onCreate START ===")
        Log.d(TAG, "========================================")

        try {
            // STEP 1: Set Content View
            Log.d(TAG, "STEP 1: Setting content view...")
            setContentView(R.layout.activity_result)
            Log.d(TAG, "✅ STEP 1 SUCCESS: Layout inflated")

            // STEP 2: Get Intent Data
            Log.d(TAG, "STEP 2: Getting intent data...")
            val score = intent?.getIntExtra("score", -999) ?: -999
            val total = intent?.getIntExtra("total", -999) ?: -999

            Log.d(TAG, "   Score received: $score")
            Log.d(TAG, "   Total received: $total")

            if (score == -999 || total == -999) {
                Log.e(TAG, "❌ STEP 2 FAILED: Invalid data (score=$score, total=$total)")
                Toast.makeText(this, "ERROR: Data tidak valid!\nScore: $score\nTotal: $total", Toast.LENGTH_LONG).show()
                finish()
                return
            }
            Log.d(TAG, "✅ STEP 2 SUCCESS: Data valid")

            // STEP 3: Get Questions
            Log.d(TAG, "STEP 3: Getting questions...")
            val questionList = try {
                val raw = intent?.getSerializableExtra("questions")
                Log.d(TAG, "   Raw questions type: ${raw?.javaClass?.name}")

                when (raw) {
                    is ArrayList<*> -> {
                        @Suppress("UNCHECKED_CAST")
                        (raw as ArrayList<Quiz>).also {
                            Log.d(TAG, "   Questions size: ${it.size}")
                        }
                    }
                    null -> {
                        Log.w(TAG, "   Questions is NULL, using QuizData")
                        ArrayList(QuizData.questions)
                    }
                    else -> {
                        Log.w(TAG, "   Questions wrong type, using QuizData")
                        ArrayList(QuizData.questions)
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "   Exception getting questions: ${e.message}", e)
                ArrayList(QuizData.questions)
            }
            Log.d(TAG, "✅ STEP 3 SUCCESS: Questions loaded (${questionList.size} items)")

            // STEP 4: Get User Answers
            Log.d(TAG, "STEP 4: Getting user answers...")
            val userAnswers = try {
                intent?.getIntegerArrayListExtra("userAnswers")?.also {
                    Log.d(TAG, "   UserAnswers size: ${it.size}")
                } ?: arrayListOf<Int>().also {
                    Log.w(TAG, "   UserAnswers is NULL/empty")
                }
            } catch (e: Exception) {
                Log.e(TAG, "   Exception getting userAnswers: ${e.message}", e)
                arrayListOf()
            }
            Log.d(TAG, "✅ STEP 4 SUCCESS: UserAnswers loaded (${userAnswers.size} items)")

            // STEP 5: Find Views
            Log.d(TAG, "STEP 5: Finding views...")

            Log.d(TAG, "   Finding tvScore...")
            val tvScore = findViewById<TextView>(R.id.tvScore)
            if (tvScore == null) {
                Log.e(TAG, "❌ STEP 5 FAILED: tvScore is NULL!")
                Toast.makeText(this, "ERROR: tvScore tidak ditemukan!", Toast.LENGTH_LONG).show()
                finish()
                return
            }
            Log.d(TAG, "   ✅ tvScore found")

            Log.d(TAG, "   Finding containerAnswers...")
            val container = findViewById<LinearLayout>(R.id.containerAnswers)
            if (container == null) {
                Log.e(TAG, "❌ STEP 5 FAILED: containerAnswers is NULL!")
                Toast.makeText(this, "ERROR: containerAnswers tidak ditemukan!", Toast.LENGTH_LONG).show()
                finish()
                return
            }
            Log.d(TAG, "   ✅ containerAnswers found")

            Log.d(TAG, "   Finding btnRetry...")
            val btnRetry = findViewById<Button>(R.id.btnRetry)
            if (btnRetry == null) {
                Log.e(TAG, "❌ STEP 5 FAILED: btnRetry is NULL!")
                Toast.makeText(this, "ERROR: btnRetry tidak ditemukan!", Toast.LENGTH_LONG).show()
                finish()
                return
            }
            Log.d(TAG, "   ✅ btnRetry found")

            Log.d(TAG, "   Finding btnFinish...")
            val btnFinish = findViewById<Button>(R.id.btnFinish)
            if (btnFinish == null) {
                Log.e(TAG, "❌ STEP 5 FAILED: btnFinish is NULL!")
                Toast.makeText(this, "ERROR: btnFinish tidak ditemukan!", Toast.LENGTH_LONG).show()
                finish()
                return
            }
            Log.d(TAG, "   ✅ btnFinish found")

            Log.d(TAG, "✅ STEP 5 SUCCESS: All views found")

            // STEP 6: Calculate & Set Score
            Log.d(TAG, "STEP 6: Setting score text...")
            val percentage = if (total > 0) {
                (score.toDouble() / total * 100).toInt()
            } else {
                0
            }
            tvScore.text = "Nilai Kamu: $percentage%\n($score dari $total soal)"
            Log.d(TAG, "✅ STEP 6 SUCCESS: Score text set to: $percentage%")

            // STEP 7: Populate Container
            Log.d(TAG, "STEP 7: Populating container...")
            container.removeAllViews()

            val itemCount = minOf(questionList.size, userAnswers.size)
            Log.d(TAG, "   Item count: $itemCount")

            if (itemCount == 0) {
                Log.w(TAG, "   No items to display")
                val tv = TextView(this)
                tv.text = "Tidak ada detail jawaban untuk ditampilkan."
                tv.textSize = 16f
                tv.setTextColor(0xFFFFFFFF.toInt())
                tv.setPadding(20, 20, 20, 20)
                container.addView(tv)
            } else {
                val previewCount = minOf(3, itemCount)
                Log.d(TAG, "   Showing $previewCount preview items")

                for (i in 0 until previewCount) {
                    try {
                        val q = questionList[i]
                        val uaIndex = userAnswers[i]
                        val correctIndex = q.correctIndex

                        val uaText = if (uaIndex >= 0 && uaIndex < q.options.size) {
                            q.options[uaIndex]
                        } else "—"

                        val caText = if (correctIndex >= 0 && correctIndex < q.options.size) {
                            q.options[correctIndex]
                        } else "—"

                        val tv = TextView(this)
                        tv.text = """
                            • Soal ${i + 1}
                            ${q.question}
                            
                            Jawaban Kamu  : $uaText
                            Jawaban Benar : $caText
                        """.trimIndent()

                        tv.textSize = 14f
                        tv.setPadding(20, 20, 20, 20)
                        tv.setTextColor(0xFF000000.toInt())
                        tv.setBackgroundColor(
                            if (uaIndex == correctIndex) 0xFFDFFFD6.toInt()
                            else 0xFFFFD6D6.toInt()
                        )

                        val params = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )
                        params.setMargins(0, 10, 0, 10)
                        tv.layoutParams = params

                        container.addView(tv)
                        Log.d(TAG, "   Added item ${i + 1}")
                    } catch (e: Exception) {
                        Log.e(TAG, "   Error adding item $i: ${e.message}", e)
                    }
                }

                if (itemCount > 3) {
                    val tvMore = TextView(this)
                    tvMore.text = "\n... dan ${itemCount - 3} soal lainnya.\n\nKlik FINISH untuk detail lengkap."
                    tvMore.textSize = 14f
                    tvMore.setPadding(20, 20, 20, 20)
                    tvMore.setTextColor(0xFFFFFFFF.toInt())
                    container.addView(tvMore)
                    Log.d(TAG, "   Added 'more' info")
                }
            }
            Log.d(TAG, "✅ STEP 7 SUCCESS: Container populated")

            // STEP 8: Setup Buttons
            Log.d(TAG, "STEP 8: Setting up button listeners...")

            btnRetry.setOnClickListener {
                Log.d(TAG, "🔄 Retry button clicked")
                try {
                    startActivity(Intent(this, QuizActivity::class.java))
                    finish()
                } catch (e: Exception) {
                    Log.e(TAG, "Error starting QuizActivity: ${e.message}", e)
                    Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }

            btnFinish.setOnClickListener {
                Log.d(TAG, "✅ Finish button clicked")
                try {
                    val intent = Intent(this, FinishActivity::class.java)
                    intent.putExtra("score", score)
                    intent.putExtra("total", total)
                    intent.putExtra("questions", ArrayList(questionList))
                    intent.putExtra("userAnswers", ArrayList(userAnswers))
                    startActivity(intent)
                    finish()
                } catch (e: Exception) {
                    Log.e(TAG, "Error starting FinishActivity: ${e.message}", e)
                    Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }

            Log.d(TAG, "✅ STEP 8 SUCCESS: Buttons ready")

            Log.d(TAG, "========================================")
            Log.d(TAG, "=== ResultActivity READY & SHOWING ===")
            Log.d(TAG, "========================================")

        } catch (e: Exception) {
            Log.e(TAG, "========================================")
            Log.e(TAG, "💥💥💥 FATAL ERROR 💥💥💥")
            Log.e(TAG, "Error message: ${e.message}")
            Log.e(TAG, "========================================")
            e.printStackTrace()
            Toast.makeText(this, "CRASH!\n${e.message}\n\nCEK LOGCAT!", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}