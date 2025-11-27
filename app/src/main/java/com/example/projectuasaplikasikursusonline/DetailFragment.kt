package com.example.projectuasaplikasikursusonline

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)



        // Ambil data dari Bundle (bukan Safe Args)
        val imageRes = arguments?.getInt("imageRes") ?: 0
        val title = arguments?.getString("title") ?: ""
        val tutorName = arguments?.getString("tutorName") ?: ""
        val tutorImage = arguments?.getInt("tutorImage") ?: 0
        val description = arguments?.getString("description") ?: ""

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



        return view
    }
}
