package com.example.projectuasaplikasikursusonline

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.content.Context


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {

            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Username dan Password tidak boleh kosong!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "Welcome $username", Toast.LENGTH_SHORT).show()

            // ================================
            // SIMPAN ke SharedPreferences
            // ================================
            val sharedPref = getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()

            editor.putString("fullname", username)
            editor.putString("password", password)

            editor.apply()
            // ================================

            // Pindah ke HomeActivity
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)

            finish()
        }
    }
}