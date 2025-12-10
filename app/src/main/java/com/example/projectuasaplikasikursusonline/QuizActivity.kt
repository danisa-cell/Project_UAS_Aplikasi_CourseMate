package com.example.projectuasaplikasikursusonline

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.projectuasaplikasikursusonline.R

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
    private lateinit var btnBack: ImageView

    // 🔥 LIST SOAL DIACAK
    private val questionList = QuizData.questions.shuffled()

    // 🔥 WAJIB PAKAI ArrayList AGAR BISA DIKIRIM LEWAT INTENT
    private val userAnswers = ArrayList<Int>()

    private lateinit var courseId: String
    private lateinit var courseTitle: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        // 🔥 TERIMA DATA DARI CourseDetailActivity
        courseId = intent.getStringExtra("courseId") ?: ""
        courseTitle = intent.getStringExtra("courseTitle") ?: ""

        // 🔥 INISIALISASI VIEW
        tvNumber = findViewById(R.id.tvNumber)
        tvQuestion = findViewById(R.id.tvQuestion)
        rgOptions = findViewById(R.id.rgOptions)

        opt1 = findViewById(R.id.opt1)
        opt2 = findViewById(R.id.opt2)
        opt3 = findViewById(R.id.opt3)
        opt4 = findViewById(R.id.opt4)
        opt5 = findViewById(R.id.opt5)

        btnNext = findViewById(R.id.btnNext)
        btnBack = findViewById(R.id.btnBack)

        // 🔥 KLIK BACK KELUAR
        btnBack.setOnClickListener { finish() }

        // 🔥 MUAT SOAL PERTAMA
        loadQuestion()

        // 🔥 TOMBOL NEXT
        btnNext.setOnClickListener { checkAnswer() }
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

        // 🔥 HAPUS PILIHAN TERAKHIR
        rgOptions.clearCheck()

        // 🔥 UBAH TOMBOL DI SOAL TERAKHIR
        btnNext.text = if (index == questionList.lastIndex) "Hasil Quiz" else "Next"
    }

    private fun checkAnswer() {

        // 🔥 CEK JAWABAN YANG DIPILIH
        val chosen = when (rgOptions.checkedRadioButtonId) {
            R.id.opt1 -> 0
            R.id.opt2 -> 1
            R.id.opt3 -> 2
            R.id.opt4 -> 3
            R.id.opt5 -> 4
            else -> -1
        }

        // 🔥 VALIDASI JIKA BELUM PILIH JAWABAN
        if (chosen == -1) {
            Toast.makeText(this, "Pilih jawaban terlebih dahulu!", Toast.LENGTH_SHORT).show()
            return
        }

        // 🔥 SIMPAN JAWABAN USER
        userAnswers.add(chosen)

        // 🔥 HITUNG SKOR
        if (chosen == questionList[index].correctIndex) score++

        // 🔥 JIKA BELUM HABIS, LANJUT SOAL BERIKUTNYA
        if (index < questionList.size - 1) {
            index++
            loadQuestion()
        } else {
            // 🔥 JIKA SUDAH HABIS, PINDAH HALAMAN HASIL
            navigateToResult()
        }
    }

    private fun navigateToResult() {

        // 🔥 BAGIAN INTENT YANG KAMU CARI!!!
        val intent = Intent(this, ResultActivity::class.java)
        // ---- Jika halaman kamu FinishActivity, ganti ke:
        // val intent = Intent(this, FinishActivity::class.java)

        // 🔥 KIRIM SKOR
        intent.putExtra("score", score)
        intent.putExtra("total", questionList.size)

        // 🔥 KIRIM DAFTAR SOAL (HARUS ArrayList)
        intent.putExtra("questions", ArrayList(questionList))

        // 🔥 KIRIM JAWABAN USER
        intent.putExtra("userAnswers", userAnswers)

        // 🔥 KIRIM DATA COURSE
        intent.putExtra("courseId", courseId)
        intent.putExtra("courseTitle", courseTitle)

        // 🔥 PINDAH ACTIVITY
        startActivity(intent)
        finish()
    }
}
