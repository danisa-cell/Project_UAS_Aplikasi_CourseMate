package com.example.projectuasaplikasikursusonline

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController


class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        // ===== Tombol Back =====
        val btnBack: ImageView = view.findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        // Ambil data dari Bundle (bukan Safe Args)
        val imageRes = arguments?.getInt("imageRes") ?: 0
        val title = arguments?.getString("title") ?: ""
        val tutorName = arguments?.getString("tutorName") ?: ""
        val tutorImage = arguments?.getInt("tutorImage") ?: 0
        val description = arguments?.getString("description") ?: ""

        // --- PENYELAMAT untuk price: cek beberapa kemungkinan ---
        // 1) Kalau dikirim sebagai String (mis. "Rp 250.000")
        var price: String? = arguments?.getString("price")

        // 2) Kalau dikirim sebagai Int (mis. 150000) — konversi ke format string sederhana
        if (price == null) {
            val priceInt = arguments?.getInt("price")
            if (priceInt != null && priceInt != 0) {
                price = "Rp ${priceInt}"
            }
        }

        // 3) Jika masih null, beri default supaya tidak crash
        if (price == null) price = "Rp 0"

        // Samain dengan XML
        val imgCourse: ImageView = view.findViewById(R.id.imgThumbnail)
        val txtTitle: TextView = view.findViewById(R.id.txtTitle)
        val txtTutor: TextView = view.findViewById(R.id.txtTutor)
        val imgTutor: ImageView = view.findViewById(R.id.imgTutor)
        val txtDesc: TextView = view.findViewById(R.id.txtDescription)

        // Set data ke layout
        imgCourse.setImageResource(imageRes)
        txtTitle.text = title
        txtTutor.text = tutorName
        imgTutor.setImageResource(tutorImage)
        txtDesc.text = description

        //  Button Pesan Sekarang
        val btnPesan: Button = view.findViewById(R.id.btnPesan)
        btnPesan.setOnClickListener {
            val bundle = Bundle().apply {
                putInt("imageRes", imageRes)
                putString("title", title)
                putString("tutorName", tutorName)
                putInt("tutorImage", tutorImage)
                putString("price", price)
            }

            findNavController().navigate(
                R.id.action_detailFragment_to_paymentFragment,
                bundle
            )
        }

        return view
    }
}
