package com.example.projectuasaplikasikursusonline

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.content.Context
import com.example.projectuasaplikasikursusonline.R


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // onCreate adalah fungsi dengan function type:
        //    (Bundle?) -> Unit
        // Dipanggil pertama kali saat Activity dibuat.

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // setContentView adalah function call untuk menampilkan layout XML.

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        // findViewById adalah generic function yang mengembalikan tipe tertentu.
        // Ini contoh function type: (Int) -> View

        btnLogin.setOnClickListener {
            // ===========================
            // LAMBDA FUNCTION
            // ===========================
            // setOnClickListener menerima lambda → materi dosen:
            // contoh higher-order function karena menerima fungsi sebagai parameter.

            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()
            // .trim() adalah fungsi yang mengembalikan String (function type: () -> String)

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Username dan Password tidak boleh kosong!", Toast.LENGTH_SHORT).show()
                // Toast.makeText → contoh fungsi yang mengembalikan object Toast
                return@setOnClickListener
                // return dengan label → kembali ke lambda, bukan return Activity
            }

            Toast.makeText(this, "Welcome $username", Toast.LENGTH_SHORT).show()

            // ================================
            // SIMPAN ke SharedPreferences
            // ================================
            val sharedPref = getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
            // getSharedPreferences adalah higher-order system API (menggunakan internal callback I/O)

            val editor = sharedPref.edit()

            editor.putString("fullname", username)
            editor.putString("password", password)
            // putString adalah function dengan type:
            // (String, String?) -> SharedPreferences.Editor

            editor.apply()
            // apply() adalah asynchronous higher-order function
            // (karena sistem menjalankan commit di thread lain)

            // ================================

            // Pindah ke HomeActivity
            val intent = Intent(this, HomeActivity::class.java)
            // Intent → analogi seperti passing parameter antar function

            startActivity(intent)
            // startActivity adalah function call yang menjalankan Activity lain.

            finish()
            // finish() menghapus Activity dari stack (function type: () -> Unit)
        }
    }
}
