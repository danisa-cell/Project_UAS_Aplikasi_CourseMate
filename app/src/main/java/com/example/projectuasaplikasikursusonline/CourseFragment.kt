package com.example.projectuasaplikasikursusonline

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CourseFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MycourseAdapter
    private lateinit var listCourse: ArrayList<MycourseModel>
    private lateinit var etSearch: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_course2, container, false)

        recyclerView = view.findViewById(R.id.rvMyCourse)
        etSearch = view.findViewById(R.id.etSearch)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        loadData()

        adapter = MycourseAdapter(listCourse) { selected ->
            val intent = Intent(requireContext(), CourseDetailActivity::class.java)
            intent.putExtra("title", selected.title)
            startActivity(intent)
        }

        recyclerView.adapter = adapter

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterData(s.toString())
            }
        })

        return view
    }

    // ✅ TAMBAHKAN INI - KUNCI UTAMA REFRESH PROGRESS
    override fun onResume() {
        super.onResume()

        // Refresh adapter untuk update progress
        if (::adapter.isInitialized) {
            adapter.notifyDataSetChanged()
        }
    }

    private fun filterData(keyword: String) {
        val filteredList = ArrayList<MycourseModel>()

        if (keyword.isEmpty()) {
            filteredList.addAll(listCourse)
        } else {
            for (course in listCourse) {
                if (course.title.lowercase().contains(keyword.lowercase())) {
                    filteredList.add(course)
                }
            }
        }

        adapter.updateList(filteredList)
    }

    private fun loadData() {
        listCourse = ArrayList()

        // ✅ HAPUS parameter ke-4 (progress hardcoded)
        listCourse.add(MycourseModel("course_1", "Pengenalan Kotlin", R.drawable.ic_course))
        listCourse.add(MycourseModel("course_2", "Tipe Data & Variabel", R.drawable.ic_tipedata))
        listCourse.add(MycourseModel("course_3", "Operator & Expression", R.drawable.ic_operator))
        listCourse.add(MycourseModel("course_4", "Percabangan (If/Else)", R.drawable.ic_percabangan))
        listCourse.add(MycourseModel("course_5", "Perulangan (Loop)", R.drawable.ic_perulangan))
        listCourse.add(MycourseModel("course_6", "Function & Scope", R.drawable.ic_function))
        listCourse.add(MycourseModel("course_7", "OOP: Class & Object", R.drawable.ic_oop))
        listCourse.add(MycourseModel("course_8", "Constructor & Init", R.drawable.ic_init))
        listCourse.add(MycourseModel("course_9", "Inheritance Kotlin", R.drawable.ic_functiongraph))
        listCourse.add(MycourseModel("course_10", "Abstract & Interface", R.drawable.ic_abstrak))
        listCourse.add(MycourseModel("course_11", "Data Class", R.drawable.ic_dataclass))
        listCourse.add(MycourseModel("course_12", "Collection List", R.drawable.ic_collectionlist))
        listCourse.add(MycourseModel("course_13", "Collection Map", R.drawable.ic_collectionmap))
        listCourse.add(MycourseModel("course_14", "Exception Handling", R.drawable.ic_exception))
        listCourse.add(MycourseModel("course_15", "Make App Android", R.drawable.ic_appandroid))
    }
}