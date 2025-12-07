package com.example.projectuasaplikasikursusonline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class CourseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_course2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvMycourse = view.findViewById<RecyclerView>(R.id.rvMycourse)

        val listCourse = listOf(
            MycourseModel("Pengenalan Kotlin", 12),
            MycourseModel("Tipe Data & Variabel", 0),
            MycourseModel("Quiz & Latihan", 0)
        )

        rvMycourse.layoutManager = LinearLayoutManager(requireContext())
        rvMycourse.adapter = MycourseAdapter(listCourse)
    }

}

