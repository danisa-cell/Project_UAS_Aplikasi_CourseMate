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
import com.example.projectuasaplikasikursusonline.R

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        // ===== Back Button =====
        val btnBack: ImageView = view.findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        // ===== Ambil data dari Bundle =====
        val imageRes = arguments?.getInt("imageRes") ?: 0
        val title = arguments?.getString("title") ?: ""
        val tutorName = arguments?.getString("tutorName") ?: ""
        val tutorImage = arguments?.getInt("tutorImage") ?: 0
        val description = arguments?.getString("description") ?: ""

        // Harga - aman dari null/crash
        var price = arguments?.getString("price")
        if (price.isNullOrEmpty()) {
            val priceInt = arguments?.getInt("price") ?: 0
            price = "Rp ${priceInt}"
        }
        if (price.isNullOrEmpty()) price = "Rp 0"

        // ===== View dari XML =====
        val imgCourse: ImageView = view.findViewById(R.id.imgThumbnail)
        val txtTitle: TextView = view.findViewById(R.id.txtTitle)
        val txtTutor: TextView = view.findViewById(R.id.txtTutor)
        val imgTutor: ImageView = view.findViewById(R.id.imgTutor)
        val txtDesc: TextView = view.findViewById(R.id.txtDescription)

        // ===== Set Data =====
        imgCourse.setImageResource(imageRes)
        txtTitle.text = title
        txtTutor.text = tutorName
        imgTutor.setImageResource(tutorImage)
        txtDesc.text = description

        // ===== Button "Pesan Sekarang" =====
        val btnPesan: Button = view.findViewById(R.id.btnPesan)
        btnPesan.setOnClickListener {
            val bundle = Bundle().apply {
                putInt("imageRes", imageRes)
                putString("title", title)
                putString("tutorName", tutorName)
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
