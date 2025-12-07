package com.example.projectuasaplikasikursusonline

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CourseDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_detail)

        val title = intent.getStringExtra("title") ?: "No Title"
        val progress = intent.getIntExtra("progress", 0)

        val tvTitle = findViewById<TextView>(R.id.tvCourseTitle)
        val tvDesc = findViewById<TextView>(R.id.tvCourseDescription)
        val btnQuiz = findViewById<Button>(R.id.btnQuiz)
        val btnBack = findViewById<ImageView>(R.id.btnBack)

        tvTitle.text = title

        // Biar keren, bikin deskripsi dummy dulu
        tvDesc.text = """
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
            Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
            
            Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut 
            aliquip ex ea commodo consequat.
        """.trimIndent()

        btnBack.setOnClickListener { finish() }

        btnQuiz.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra("title", title)
            startActivity(intent)
        }
    }
}
