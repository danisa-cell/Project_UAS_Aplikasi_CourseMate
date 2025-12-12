package com.example.projectuasaplikasikursusonline
// Package file berada

import android.content.Intent
// Untuk berpindah Activity

import android.os.Bundle
// Bundle untuk menyimpan/restore state

import android.widget.*
// Import semua widget: TextView, Button, RadioGroup, dst.

import androidx.appcompat.app.AppCompatActivity
// Base class aktivitas

import com.example.projectuasaplikasikursusonline.R
// Import resource XML

class QuizActivity : AppCompatActivity() {

    private var index = 0
    // Index soal yang sedang ditampilkan

    private var score = 0
    // Skor akhir quiz

    private lateinit var tvNumber: TextView
    private lateinit var tvQuestion: TextView
    private lateinit var rgOptions: RadioGroup

    // RadioButton untuk pilihan A–E
    private lateinit var opt1: RadioButton
    private lateinit var opt2: RadioButton
    private lateinit var opt3: RadioButton
    private lateinit var opt4: RadioButton
    private lateinit var opt5: RadioButton

    private lateinit var btnNext: Button
    private lateinit var btnPrev: Button
    private lateinit var btnBack: ImageView   // Tombol keluar quiz

    private val questionList = QuizData.questions.shuffled()
    // List soal diacak sebelum digunakan

    private val userAnswers = ArrayList<Int>()
    // Menyimpan jawaban user per soal (index: 0..4)

    private lateinit var courseId: String
    // ID course, dipakai menyimpan progress

    private lateinit var courseTitle: String
    // Nama course untuk tampilan di ResultActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        // Set layout XML quiz

        // Ambil data course dari Intent
        courseId = intent.getStringExtra("courseId") ?: ""
        courseTitle = intent.getStringExtra("courseTitle") ?: ""

        // ===============================
        // INIT VIEW (mapping ke XML)
        // ===============================
        tvNumber = findViewById(R.id.tvNumber)
        tvQuestion = findViewById(R.id.tvQuestion)
        rgOptions = findViewById(R.id.rgOptions)

        opt1 = findViewById(R.id.opt1)
        opt2 = findViewById(R.id.opt2)
        opt3 = findViewById(R.id.opt3)
        opt4 = findViewById(R.id.opt4)
        opt5 = findViewById(R.id.opt5)

        btnNext = findViewById(R.id.btnNext)
        btnPrev = findViewById(R.id.btnPrev)
        btnBack = findViewById(R.id.btnBack)

        // ===============================
        // 🔥 Tombol keluar quiz
        // ===============================
        btnBack.setOnClickListener { finish() }
        // Menutup activity quiz → kembali ke halaman sebelumnya

        // ===============================
        // 🔥 Tombol soal sebelumnya
        // ===============================
        btnPrev.setOnClickListener { goPrevQuestion() }

        // ===============================
        // 🔥 Tampilkan soal pertama
        // ===============================
        loadQuestion()

        // ===============================
        // 🔥 Tombol NEXT (lanjut / selesai)
        // ===============================
        btnNext.setOnClickListener { checkAnswer() }
    }

    private fun loadQuestion() {
        // Mengambil soal berdasarkan index
        val q = questionList[index]

        // Menampilkan nomor soal
        tvNumber.text = "Soal ${index + 1} dari ${questionList.size}"

        // Isi pertanyaan
        tvQuestion.text = q.question

        // Isi pilihan jawaban
        opt1.text = q.options[0]
        opt2.text = q.options[1]
        opt3.text = q.options[2]
        opt4.text = q.options[3]
        opt5.text = q.options[4]

        // Hapus pilihan sebelumnya
        rgOptions.clearCheck()

        // Jika user sudah menjawab sebelumnya, isi kembali sesuai jawaban yang tersimpan
        if (index < userAnswers.size) {
            when (userAnswers[index]) {
                0 -> opt1.isChecked = true
                1 -> opt2.isChecked = true
                2 -> opt3.isChecked = true
                3 -> opt4.isChecked = true
                4 -> opt5.isChecked = true
            }
        }

        // Jika ini soal terakhir, ubah tombol menjadi "Hasil Quiz"
        btnNext.text =
            if (index == questionList.lastIndex) "Hasil Quiz"
            else "Next"
    }

    private fun checkAnswer() {
        // Ambil pilihan yang user klik
        val chosen = when (rgOptions.checkedRadioButtonId) {
            R.id.opt1 -> 0
            R.id.opt2 -> 1
            R.id.opt3 -> 2
            R.id.opt4 -> 3
            R.id.opt5 -> 4
            else -> -1
        }

        // Jika user belum memilih opsi
        if (chosen == -1) {
            Toast.makeText(this, "Pilih jawaban terlebih dahulu!", Toast.LENGTH_SHORT).show()
            return
        }

        // Simpan jawaban: update jika sebelumnya sudah ada
        if (index < userAnswers.size) {
            userAnswers[index] = chosen
        } else {
            userAnswers.add(chosen)
        }

        // Jika masih ada soal berikutnya → pindah
        if (index < questionList.size - 1) {
            index++
            loadQuestion()
        } else {
            // Jika ini soal terakhir → pindah ke ResultActivity
            navigateToResult()
        }
    }

    private fun goPrevQuestion() {
        // Tidak bisa mundur jika masih di soal pertama
        if (index == 0) {
            Toast.makeText(this, "Ini soal pertama!", Toast.LENGTH_SHORT).show()
            return
        }

        index--
        loadQuestion()
    }

    private fun navigateToResult() {

        // Hitung skor dari jawaban user
        score = 0
        for (i in userAnswers.indices) {
            if (userAnswers[i] == questionList[i].correctIndex) {
                score++
            }
        }

        // Pindah ke ResultActivity + bawa semua data perlu
        val intent = Intent(this, ResultActivity::class.java)

        intent.putExtra("score", score)
        intent.putExtra("total", questionList.size)
        intent.putExtra("questions", ArrayList(questionList))
        intent.putExtra("userAnswers", userAnswers)
        intent.putExtra("courseId", courseId)
        intent.putExtra("courseTitle", courseTitle)

        startActivity(intent)
        finish()
    }
}
