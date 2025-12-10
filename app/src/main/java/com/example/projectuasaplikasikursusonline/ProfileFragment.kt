package com.example.projectuasaplikasikursusonline

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.text.InputType
import androidx.appcompat.app.AppCompatActivity
import com.example.projectuasaplikasikursusonline.R

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,             // Fungsi menerima parameter → contoh **function parameter**
        container: ViewGroup?,                // Function type: (LayoutInflater, ViewGroup?, Bundle?) -> View?
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        // inflate() adalah **higher-order function** bawaan Android karena menerima callback internal

        // View dari layout
        val etName = view.findViewById<EditText>(R.id.etName)
        val etPassword = view.findViewById<EditText>(R.id.etPassword)
        val btnLogout = view.findViewById<Button>(R.id.btnLogout)
        val btnShow = view.findViewById<ImageView>(R.id.btnShowPassword)

        // ============================================
        //        AMBIL DATA DARI LOGIN (SharedPref)
        // ============================================
        val sharedPref = requireActivity()
            .getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        // requireActivity() → function reference implicit (memanggil fungsi milik Activity)

        val username = sharedPref.getString("fullname", "")
        val password = sharedPref.getString("password", "")
        // getString() → contoh function yang mengembalikan nilai (function type: () -> String?)

        etName.setText(username)
        etPassword.setText(password)

        // ============================================
        //          SHOW / HIDE PASSWORD FIXED
        // ============================================
        var visible = false // variabel biasa, nanti dipakai dalam lambda onClick

        btnShow.setOnClickListener {
            // onClickListener adalah **lambda expression**
            // bentuknya { /* kode dijalankan saat tombol diklik */ }

            visible = !visible   // toggle boolean (logika UI)

            if (visible) {
                // Password terlihat
                etPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                btnShow.setImageResource(R.drawable.ic_eye_on)
            } else {
                // Password disembunyikan
                etPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                btnShow.setImageResource(R.drawable.ic_eye_of)
            }

            // Cursor tetap di akhir
            etPassword.setSelection(etPassword.text.length)
            // setSelection() → contoh **function call** yang mengembalikan unit
        }

        // ============================================
        //                  LOGOUT
        // ============================================
        btnLogout.setOnClickListener {
            // Ini juga lambda (materi: **lambda function digunakan sebagai event handler**)

            sharedPref.edit().clear().apply()
            // apply() → higher-order function karena menerima lambda internal untuk transaksi commit

            val intent = Intent(requireContext(), LoginActivity::class.java)
            // Intent adalah object untuk berpindah Activity → analogi passing parameter ke function lain

            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            // Menggabungkan flag pakai operator OR → mirip function type (mengirim 2 parameter status)

            startActivity(intent)
            // startActivity() = function call → mengirim Intent (param) seperti mengirim function input
        }

        return view
        // Mengembalikan View? sesuai function type: () -> View?
    }
}
