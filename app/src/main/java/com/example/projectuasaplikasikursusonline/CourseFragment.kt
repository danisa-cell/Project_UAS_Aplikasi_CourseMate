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
            intent.putExtra("progress", selected.progress)
            startActivity(intent)
        }

        recyclerView.adapter = adapter

        // ✅ AKTIFKAN SEARCH
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterData(s.toString())
            }
        })

        return view
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

        listCourse.add(MycourseModel("Pengenalan Kotlin", 12, R.drawable.ic_course))
        listCourse.add(MycourseModel("Tipe Data & Variabel", 40, R.drawable.ic_tipedata))
        listCourse.add(MycourseModel("Operator & Expression", 55, R.drawable.ic_operator))
        listCourse.add(MycourseModel("Percabangan (If/Else)", 75, R.drawable.ic_percabangan))
        listCourse.add(MycourseModel("Perulangan (Loop)", 33, R.drawable.ic_perulangan))
        listCourse.add(MycourseModel("Function & Scope", 68, R.drawable.ic_function))
        listCourse.add(MycourseModel("OOP: Class & Object", 80, R.drawable.ic_oop))
        listCourse.add(MycourseModel("Constructor & Init", 45, R.drawable.ic_init))
        listCourse.add(MycourseModel("Inheritance Kotlin", 52, R.drawable.ic_functiongraph))
        listCourse.add(MycourseModel("Abstract & Interface", 70, R.drawable.ic_abstrak))
        listCourse.add(MycourseModel("Data Class", 24, R.drawable.ic_dataclass))
        listCourse.add(MycourseModel("Collection List", 61, R.drawable.ic_collectionlist))
        listCourse.add(MycourseModel("Collection Map", 49, R.drawable.ic_collectionmap))
        listCourse.add(MycourseModel("Exception Handling", 37, R.drawable.ic_exception))
        listCourse.add(MycourseModel("Make app android", 90, R.drawable.ic_appandroid))
    }
}
