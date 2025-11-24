package com.example.projectuasaplikasikursusonline

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.projectuasaplikasikursusonline.R

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // Ambil EditText dari layout
        val etName = view.findViewById<EditText>(R.id.etName)
        val etPassword = view.findViewById<EditText>(R.id.etPassword)
        val btnLogout = view.findViewById<Button>(R.id.btnLogout)

        // Ambil data kiriman dari MainActivity
        val username = arguments?.getString("USERNAME")
        val password = arguments?.getString("PASSWORD")

        // Tampilkan ke EditText
        etName.setText(username)
        etPassword.setText(password)

        // =============================
        //        LOGOUT BUTTON
        // =============================
        btnLogout.setOnClickListener {

            // Hapus session (jika ada)
            val sharedPref = requireActivity()
                .getSharedPreferences("USER", AppCompatActivity.MODE_PRIVATE)

            sharedPref.edit().clear().apply()

            // Pindah ke LoginActivity
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

            // Tutup MainActivity
            requireActivity().finish()
        }

        return view
    }
}
