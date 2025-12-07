package com.example.projectuasaplikasikursusonline

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CourseFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MycourseAdapter
    private lateinit var listCourse: ArrayList<MycourseModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_course2, container, false)

        recyclerView = view.findViewById(R.id.rvMyCourse)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        loadData()

        adapter = MycourseAdapter(listCourse) { selected ->
            val intent = Intent(requireContext(), CourseDetailActivity::class.java)
            intent.putExtra("title", selected.title)
            intent.putExtra("progress", selected.progress)
            startActivity(intent)
        }

        recyclerView.adapter = adapter

        return view
    }

    private fun loadData() {
        listCourse = ArrayList()

        listCourse.add(MycourseModel("Pengenalan Kotlin", 12))
        listCourse.add(MycourseModel("Tipe Data & Variabel", 0))
        listCourse.add(MycourseModel("Operator & Expression", 0))
        listCourse.add(MycourseModel("Percabangan (If/Else)", 0))
        listCourse.add(MycourseModel("Perulangan (Loop)", 0))
        listCourse.add(MycourseModel("Function & Scope", 0))
        listCourse.add(MycourseModel("OOP: Class & Object", 0))
        listCourse.add(MycourseModel("Constructor & Init", 0))
        listCourse.add(MycourseModel("Inheritance Kotlin", 0))
        listCourse.add(MycourseModel("Abstract & Interface", 0))
        listCourse.add(MycourseModel("Data Class", 0))
        listCourse.add(MycourseModel("Collection List", 0))
        listCourse.add(MycourseModel("Collection Map", 0))
        listCourse.add(MycourseModel("Exception Handling", 0))
        listCourse.add(MycourseModel("Quiz & Latihan", 0))
    }
}
