package com.example.projectuasaplikasikursonline

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Ambil data username dari LoginActivity
        val username = intent.getStringExtra("username")

        // Tampilkan nama pada EditText etName
        val etName = findViewById<EditText>(R.id.etName)
        etName.setText(username ?: "Nama Kamu")

        val btnLogout = findViewById<Button>(R.id.btnLogout)

        // ----------------------- LOGOUT -----------------------
        btnLogout.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        // ===================== BOTTOM NAVBAR =====================
        val navHome = findViewById<LinearLayout>(R.id.navHome)
        val navHistory = findViewById<LinearLayout>(R.id.navHistory)
        val navCourse = findViewById<LinearLayout>(R.id.navCourse)
        val navProfile = findViewById<LinearLayout>(R.id.navProfile)

        // HOME
        navHome.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        // HISTORY
        navHistory.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }

        // MY COURSE
        navCourse.setOnClickListener {
            startActivity(Intent(this, MyCourseActivity::class.java))
        }

        // PROFILE (halaman ini) → tidak pindah ke mana-mana
        navProfile.setOnClickListener {
            Toast.makeText(this, "Kamu sudah di halaman Profile", Toast.LENGTH_SHORT).show()
        }
    }
}
