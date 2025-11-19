package com.example.projectuasaplikasikursonline

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import com.example.projectuasaplikasikursusonline.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val editUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {

            val username = editUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            // Login bebas asal tidak kosong
            if (username.isNotEmpty() && password.isNotEmpty()) {

                val intent = Intent(this, ProfileActivity::class.java)
                intent.putExtra("USER_NAME", username)

                startActivity(intent)
                finish()  // Biar tidak kembali ke login
            } else {
                Toast.makeText(this, "Username dan password tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
