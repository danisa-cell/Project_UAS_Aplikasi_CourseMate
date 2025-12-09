package com.example.projectuasaplikasikursusonline

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectuasaplikasikursusonline.storage.CourseProgressStorage
import com.example.projectuasaplikasikursusonline.R

class CourseFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MycourseAdapter
    private lateinit var listCourse: ArrayList<MycourseModel>
    private lateinit var etSearch: EditText
    private lateinit var btnResetProgress: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_course2, container, false)

        recyclerView = view.findViewById(R.id.rvMyCourse)
        etSearch = view.findViewById(R.id.etSearch)
        btnResetProgress = view.findViewById(R.id.btnResetProgress)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        loadData()
        refreshCourseProgress()

        adapter = MycourseAdapter(listCourse) { selected ->
            val intent = Intent(requireContext(), CourseDetailActivity::class.java)
            intent.putExtra("title", selected.title)
            intent.putExtra("courseId", selected.id)
            startActivity(intent)
        }

        recyclerView.adapter = adapter

        // Search
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterData(s.toString())
            }
        })

        btnResetProgress.setOnClickListener {
            showResetSelectionDialog()
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        refreshCourseProgress()
        adapter.notifyDataSetChanged()
    }

    private fun refreshCourseProgress() {
        listCourse.forEach { course ->
            // 🔥 PAKAI TITLE (bukan id)
            val p = CourseProgressStorage.getProgress(requireContext(), course.title)
            course.progress = p
        }
    }

    // -------------------------- RESET DIALOG -------------------------- //

    private fun showResetSelectionDialog() {
        val courseNames = listCourse.map { it.title }.toTypedArray()
        val checkedItems = BooleanArray(courseNames.size)
        val selectedCourses = mutableListOf<MycourseModel>()

        AlertDialog.Builder(requireContext())
            .setTitle("Pilih Course untuk Reset")
            .setMultiChoiceItems(courseNames, checkedItems) { _, which, isChecked ->
                if (isChecked) selectedCourses.add(listCourse[which])
                else selectedCourses.remove(listCourse[which])
            }
            .setPositiveButton("Reset") { _, _ ->
                if (selectedCourses.isEmpty()) {
                    Toast.makeText(requireContext(), "Tidak ada course yang dipilih", Toast.LENGTH_SHORT).show()
                } else {
                    showResetSelectedConfirmation(selectedCourses)
                }
            }
            .setNeutralButton("Reset Semua") { _, _ ->
                showResetAllConfirmation()
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    private fun showResetSelectedConfirmation(courses: List<MycourseModel>) {
        AlertDialog.Builder(requireContext())
            .setTitle("Konfirmasi Reset")
            .setMessage("Apakah Anda yakin ingin mereset ${courses.size} course yang dipilih?")
            .setPositiveButton("Ya, Reset") { _, _ ->
                resetSelectedCourses(courses)
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    private fun showResetAllConfirmation() {
        AlertDialog.Builder(requireContext())
            .setTitle("Reset Semua Progress")
            .setMessage("Apakah Anda yakin ingin mereset semua progress? Semua kemajuan belajar Anda akan hilang.")
            .setPositiveButton("Ya, Reset Semua") { _, _ ->
                resetAllProgress()
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    private fun resetSelectedCourses(courses: List<MycourseModel>) {
        // 🔥 PAKAI TITLE (bukan id)
        courses.forEach { course ->
            CourseProgressStorage.resetCourseProgress(requireContext(), course.title)
        }

        // Refresh UI
        refreshCourseProgress()
        adapter.notifyDataSetChanged()

        Toast.makeText(requireContext(),
            "${courses.size} course berhasil direset", Toast.LENGTH_SHORT).show()
    }

    private fun resetAllProgress() {
        CourseProgressStorage.resetAllProgress(requireContext())

        // Refresh UI
        refreshCourseProgress()
        adapter.notifyDataSetChanged()

        Toast.makeText(requireContext(),
            "Semua progress berhasil direset", Toast.LENGTH_SHORT).show()
    }

    // ------------------------------ SEARCH ------------------------------ //

    private fun filterData(keyword: String) {
        val filteredList =
            if (keyword.isEmpty()) listCourse
            else listCourse.filter {
                it.title.contains(keyword, ignoreCase = true)
            }

        adapter.updateList(ArrayList(filteredList))
    }

    // ------------------------------ DATA ------------------------------ //

    private fun loadData() {
        listCourse = arrayListOf(
            MycourseModel("course_1", "Pengenalan Kotlin", R.drawable.ic_kotlin),
            MycourseModel("course_2", "Tipe Data & Variabel", R.drawable.ic_tipedata),
            MycourseModel("course_3", "Operator & Expression", R.drawable.ic_operator),
            MycourseModel("course_4", "Percabangan (If/Else)", R.drawable.ic_percabangan),
            MycourseModel("course_5", "Perulangan (Loop)", R.drawable.ic_perulangan),
            MycourseModel("course_6", "Function & Scope", R.drawable.ic_function),
            MycourseModel("course_7", "OOP: Class & Object", R.drawable.ic_oop),
            MycourseModel("course_8", "Constructor & Init", R.drawable.ic_init),
            MycourseModel("course_9", "Inheritance Kotlin", R.drawable.ic_functiongraph),
            MycourseModel("course_10", "Abstract & Interface", R.drawable.ic_abstrak),
            MycourseModel("course_11", "Data Class", R.drawable.ic_dataclass),
            MycourseModel("course_12", "Collection List", R.drawable.ic_collectionlist),
            MycourseModel("course_13", "Collection Map", R.drawable.ic_collectionmap),
            MycourseModel("course_14", "Exception Handling", R.drawable.ic_exception),
            MycourseModel("course_15", "Make App Android", R.drawable.ic_appandroid)
        )
    }
}