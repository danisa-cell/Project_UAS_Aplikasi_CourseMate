package com.example.projectuasaplikasikursusonline

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {

    private val TAG = "QuizActivity"

    private var index = 0
    private var score = 0

    private lateinit var tvNumber: TextView
    private lateinit var tvQuestion: TextView
    private lateinit var rgOptions: RadioGroup

    private lateinit var opt1: RadioButton
    private lateinit var opt2: RadioButton
    private lateinit var opt3: RadioButton
    private lateinit var opt4: RadioButton
    private lateinit var opt5: RadioButton

    private lateinit var btnNext: Button
    private lateinit var btnBack: ImageView   // ✅ tombol back

    private val questionList = QuizData.questions.shuffled()
    private val userAnswers = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        Log.d(TAG, "=== QuizActivity START ===")
        Log.d(TAG, "Total questions: ${questionList.size}")

        // Init view
        tvNumber = findViewById(R.id.tvNumber)
        tvQuestion = findViewById(R.id.tvQuestion)
        rgOptions = findViewById(R.id.rgOptions)

        opt1 = findViewById(R.id.opt1)
        opt2 = findViewById(R.id.opt2)
        opt3 = findViewById(R.id.opt3)
        opt4 = findViewById(R.id.opt4)
        opt5 = findViewById(R.id.opt5)

        btnNext = findViewById(R.id.btnNext)

        // ✅ Init tombol back
        btnBack = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()  // balik ke activity sebelumnya
        }

        loadQuestion()

        btnNext.setOnClickListener {
            checkAnswer()
        }
    }

    private fun loadQuestion() {
        val q = questionList[index]

        tvNumber.text = "Soal ${index + 1} dari ${questionList.size}"
        tvQuestion.text = q.question

        opt1.text = q.options[0]
        opt2.text = q.options[1]
        opt3.text = q.options[2]
        opt4.text = q.options[3]
        opt5.text = q.options[4]

        rgOptions.clearCheck()

        btnNext.text = if (index == questionList.lastIndex) "Hasil Quiz" else "Next"
    }

    private fun checkAnswer() {
        val chosen = when (rgOptions.checkedRadioButtonId) {
            R.id.opt1 -> 0
            R.id.opt2 -> 1
            R.id.opt3 -> 2
            R.id.opt4 -> 3
            R.id.opt5 -> 4
            else -> -1
        }

        if (chosen == -1) {
            Toast.makeText(this, "Pilih salah satu jawaban!", Toast.LENGTH_SHORT).show()
            return
        }

        userAnswers.add(chosen)

        val correct = questionList[index].correctIndex
        if (chosen == correct) score++

        if (index < questionList.size - 1) {
            index++
            loadQuestion()
        } else {
            navigateToResult()
        }
    }

    private fun navigateToResult() {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("score", score)
        intent.putExtra("total", questionList.size)
        intent.putExtra("questions", ArrayList(questionList))
        intent.putExtra("userAnswers", ArrayList(userAnswers))

        startActivity(intent)
        finish()
    }
}
