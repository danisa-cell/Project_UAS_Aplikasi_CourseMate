package com.example.projectuasaplikasikursusonline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        // View Binding manual
        val btnBack: ImageView = view.findViewById(R.id.btnBack)
        val imgThumbnail: ImageView = view.findViewById(R.id.imgThumbnail)
        val imgTutor: ImageView = view.findViewById(R.id.imgTutor)
        val txtTitle: TextView = view.findViewById(R.id.txtTitle)
        val txtTutor: TextView = view.findViewById(R.id.txtTutor)
        val txtDescription: TextView = view.findViewById(R.id.txtDescription)

        // Ambil data dari arguments
        val args = requireArguments()

        val title = args.getString("title")
        val price = args.getString("price")
        val imageRes = args.getInt("imageRes")
        val tutorName = args.getString("tutorName")
        val tutorImage = args.getInt("tutorImage")
        val description = args.getString("description")

        // ================================
        // Set ke View
        // ================================
        txtTitle.text = title
        txtTutor.text = tutorName
        imgThumbnail.setImageResource(imageRes)
        imgTutor.setImageResource(tutorImage)
        txtDescription.text = "$description\n\nHarga: $price"
        txtDescription.text = description

        // Tombol kembali
        btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        return view
    }
}
