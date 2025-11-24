package com.example.projectuasaplikasikursusonline

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {

    private lateinit var edtSearch: EditText
    private lateinit var listCourse: ListView
    private lateinit var adapter: ArrayAdapter<String>

    // contoh data kursus (boleh kamu ganti)
    private val courseList = listOf(
        "Android Development",
        "UI/UX Design",
        "Web Programming",
        "Database MySQL",
        "Java Programming",
        "Kotlin Dasar",
        "Cloud Computing",
        "Cyber Security",
        "Machine Learning",
        "Data Analytics"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        edtSearch = view.findViewById(R.id.edtSearch)
        listCourse = view.findViewById(R.id.listCourse)

        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, courseList)
        listCourse.adapter = adapter

        // fitur pencarian
        edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter.filter(s)
            }
        })

        return view
    }
}
