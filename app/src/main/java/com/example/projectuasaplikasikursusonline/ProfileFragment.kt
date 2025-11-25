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

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_profile, container, false)

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

        val username = sharedPref.getString("fullname", "")
        val password = sharedPref.getString("password", "")

        etName.setText(username)
        etPassword.setText(password)

        // ============================================
        //          SHOW / HIDE PASSWORD FIXED
        // ============================================
        var visible = false

        btnShow.setOnClickListener {
            visible = !visible

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
        }

        // ============================================
        //                  LOGOUT
        // ============================================
        btnLogout.setOnClickListener {

            sharedPref.edit().clear().apply()

            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        return view
    }
}
