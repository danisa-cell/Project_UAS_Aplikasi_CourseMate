package com.example.projectuasaplikasikursusonline.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.projectuasaplikasikursusonline.HomeActivity
import com.example.projectuasaplikasikursusonline.R

class PaymentSuccessFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_payment_success, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Tombol back kiri atas
        val btnBack = view.findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    override fun onResume() {
        super.onResume()
        // Sembunyikan bottom nav saat masuk halaman ini
        (activity as? HomeActivity)?.setBottomNavVisibility(false)
    }

    override fun onPause() {
        super.onPause()
        // Tampilkan kembali saat keluar halaman ini
        (activity as? HomeActivity)?.setBottomNavVisibility(true)
    }
}
