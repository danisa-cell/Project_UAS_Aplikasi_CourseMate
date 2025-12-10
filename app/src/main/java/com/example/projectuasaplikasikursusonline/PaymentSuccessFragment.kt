package com.example.projectuasaplikasikursusonline.fragment
// Package tempat file ini berada

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.projectuasaplikasikursusonline.HomeActivity
import com.example.projectuasaplikasikursusonline.R
// Import library dan resource yang digunakan

class PaymentSuccessFragment : Fragment() {
    // Mendefinisikan sebuah fragment bernama PaymentSuccessFragment

    override fun onCreateView(
        inflater: LayoutInflater,              // Untuk mengubah file XML jadi objek View
        container: ViewGroup?,                 // Parent layout (bisa null)
        savedInstanceState: Bundle?            // Menyimpan state saat rotasi dsb
    ): View? {
        // Mengembalikan layout XML untuk fragment ini
        return inflater.inflate(
            R.layout.fragment_payment_success, // Layout yang ingin ditampilkan
            container,                         // Ditempatkan pada parent-nya
            false                              // Jangan langsung attach ke root
        )
    }

    override fun onViewCreated(
        view: View,                            // View yang sudah selesai di-inflate
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        // Mengambil komponen tombol back dari XML
        val btnBack = view.findViewById<ImageView>(R.id.btnBack)

        // Memberi aksi ketika tombol ditekan
        btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
            // Menghapus fragment ini dari stack dan kembali ke fragment sebelumnya
        }
    }
}
