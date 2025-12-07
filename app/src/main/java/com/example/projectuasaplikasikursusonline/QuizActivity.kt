package com.example.projectuasaplikasikursusonline

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        tvNumber = findViewById(R.id.tvNumber)
        tvQuestion = findViewById(R.id.tvQuestion)
        rgOptions = findViewById(R.id.rgOptions)

        opt1 = findViewById(R.id.opt1)
        opt2 = findViewById(R.id.opt2)
        opt3 = findViewById(R.id.opt3)
        opt4 = findViewById(R.id.opt4)
        opt5 = findViewById(R.id.opt5)

        btnNext = findViewById(R.id.btnNext)

        loadQuestion()

        btnNext.setOnClickListener {
            checkAnswer()
        }
    }

    private fun loadQuestion() {
        val q = QuizData.questions[index]

        tvNumber.text = "Soal ${index + 1} dari ${QuizData.questions.size}"
        tvQuestion.text = q.question

        // Asumsikan options berisi 5 item
        opt1.text = q.options[0]
        opt2.text = q.options[1]
        opt3.text = q.options[2]
        opt4.text = q.options[3]
        opt5.text = q.options[4]

        rgOptions.clearCheck()
    }

    private fun checkAnswer() {
        val q = QuizData.questions[index]

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

        if (chosen == q.correctIndex) score++

        if (index < QuizData.questions.size - 1) {
            index++
            loadQuestion()
        } else {
            Toast.makeText(this, "Quiz selesai! Skor kamu: $score", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}
