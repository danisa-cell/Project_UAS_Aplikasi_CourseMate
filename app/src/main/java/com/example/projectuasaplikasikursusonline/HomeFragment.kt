package com.example.projectuasaplikasikursusonline

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {

    private lateinit var edtSearch: EditText
    private lateinit var rvCourse: RecyclerView
    private lateinit var adapter: CourseAdapter

    private val courseList = listOf(
        Course(
            "Android Development", "Rp 250.000", R.drawable.ic_android,
            "Budi Santoso", R.drawable.tutor_android,
            "Kelas ini membahas cara membuat aplikasi Android dari dasar hingga mahir. Kamu akan belajar layout, activity, fragment, dan data transfer antar halaman. Selain itu, materi juga mencakup penggunaan RecyclerView, API, dan Firebase. Cocok untuk pemula yang ingin menjadi Android Developer profesional."
        ),
        // ... (data lainnya tetap sama)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        edtSearch = view.findViewById(R.id.edtSearch)
        rvCourse = view.findViewById(R.id.rvCourse)

        rvCourse.layoutManager = LinearLayoutManager(requireContext())

        adapter = CourseAdapter(courseList) { selectedCourse ->

            // 🔥 Kirim data ke DetailFragment tanpa Safe Args
            val bundle = Bundle().apply {
                putString("title", selectedCourse.title)
                putString("price", selectedCourse.price)
                putInt("imageRes", selectedCourse.imageRes)
                putString("tutorName", selectedCourse.tutorName)
                putInt("tutorImage", selectedCourse.tutorImage)
                putString("description", selectedCourse.description)
            }

            findNavController().navigate(
                R.id.action_homeFragment_to_detailFragment,
                bundle
            )
        }

        rvCourse.adapter = adapter

        // 🔍 Fitur Search
        edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val filtered = courseList.filter {
                    it.title.contains(s.toString(), ignoreCase = true)
                }
                adapter.filterList(filtered)
            }
        })

        return view
    }
}
