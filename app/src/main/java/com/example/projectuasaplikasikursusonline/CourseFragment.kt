package com.example.projectuasaplikasikursusonline
// Package utama aplikasi

import android.content.Intent
// Untuk pindah Activity

import android.os.Bundle
// Untuk menyimpan state fragment

import android.text.Editable
import android.text.TextWatcher
// TextWatcher untuk fitur search realtime

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
// Untuk membuat tampilan fragment

import android.widget.Button
import android.widget.EditText
import android.widget.Toast
// Widget dan Toast

import androidx.appcompat.app.AlertDialog
// Untuk menampilkan dialog konfirmasi (reset progress)

import androidx.fragment.app.Fragment
// Fragment utama

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
// RecyclerView untuk tampilkan list course

import com.example.projectuasaplikasikursusonline.storage.CourseProgressStorage
// Kelas penyimpanan progress

import com.example.projectuasaplikasikursusonline.R
// File resource layout & drawable

class CourseFragment : Fragment() {
    // Fragment untuk menampilkan daftar course user

    private lateinit var recyclerView: RecyclerView  // RecyclerView daftar course
    private lateinit var adapter: MycourseAdapter    // Adapter menampilkan item course
    private lateinit var listCourse: ArrayList<MycourseModel> // List data course
    private lateinit var etSearch: EditText          // Input pencarian
    private lateinit var btnResetProgress: Button    // Tombol reset progress

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate (tampilkan) layout fragment_course2.xml
        val view = inflater.inflate(R.layout.fragment_course2, container, false)

        // Hubungkan variabel dengan view di XML
        recyclerView = view.findViewById(R.id.rvMyCourse)        // RecyclerView
        etSearch = view.findViewById(R.id.etSearch)              // EditText search
        btnResetProgress = view.findViewById(R.id.btnResetProgress) // Tombol reset

        // Set layout RecyclerView menjadi list vertikal
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        loadData()              // Mengisi listCourse dengan data manual
        refreshCourseProgress() // Update progress tiap course dari storage

        // Inisialisasi adapter + event klik item → buka CourseDetailActivity
        adapter = MycourseAdapter(listCourse) { selected ->
            val intent = Intent(requireContext(), CourseDetailActivity::class.java)
            intent.putExtra("title", selected.title) // Kirim title ke detail
            intent.putExtra("courseId", selected.id) // Kirim ID (opsional)
            startActivity(intent)                    // Pindah activity
        }

        recyclerView.adapter = adapter // Pasang adapter ke RecyclerView

        // Fitur Search Realtime
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {} // Tidak dipakai
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            // Dipanggil ketika user sedang mengetik
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterData(s.toString()) // Filter daftar sesuai keyword
            }
        })

        // Tombol reset progress → buka dialog pilihan
        btnResetProgress.setOnClickListener {
            showResetSelectionDialog()
        }

        return view // Kembalikan view fragment
    }

    override fun onResume() {
        super.onResume()
        refreshCourseProgress()  // Update ulang progress saat kembali ke fragment
        adapter.notifyDataSetChanged() // Refresh tampilan list
    }

    private fun refreshCourseProgress() {
        // Loop semua course untuk ambil progress dari storage
        listCourse.forEach { course ->
            // 🔥 PROGRESS BERDASARKAN TITLE (unik)
            val p = CourseProgressStorage.getProgress(requireContext(), course.title)
            course.progress = p // Simpan ke model
        }
    }

    // -------------------------- RESET DIALOG -------------------------- //

    private fun showResetSelectionDialog() {
        val courseNames = listCourse.map { it.title }.toTypedArray() // List nama course
        val checkedItems = BooleanArray(courseNames.size)            // Checkbox semua false
        val selectedCourses = mutableListOf<MycourseModel>()         // List pilihan user

        // Buat dialog untuk pilih course yang mau direset
        AlertDialog.Builder(requireContext())
            .setTitle("Pilih Course untuk Reset")
            .setMultiChoiceItems(courseNames, checkedItems) { _, which, isChecked ->
                // Jika dicentang → tambahkan / hapus dari list selected
                if (isChecked) selectedCourses.add(listCourse[which])
                else selectedCourses.remove(listCourse[which])
            }
            .setPositiveButton("Reset") { _, _ ->
                // Jika tidak ada dipilih
                if (selectedCourses.isEmpty()) {
                    Toast.makeText(requireContext(), "Tidak ada course yang dipilih", Toast.LENGTH_SHORT).show()
                } else {
                    showResetSelectedConfirmation(selectedCourses)
                }
            }
            .setNeutralButton("Reset Semua") { _, _ ->
                showResetAllConfirmation() // Dialog reset semua progress
            }
            .setNegativeButton("Batal", null) // Tutup dialog
            .show()
    }

    private fun showResetSelectedConfirmation(courses: List<MycourseModel>) {
        // Dialog konfirmasi reset course terpilih
        AlertDialog.Builder(requireContext())
            .setTitle("Konfirmasi Reset")
            .setMessage("Apakah Anda yakin ingin mereset ${courses.size} course yang dipilih?")
            .setPositiveButton("Ya, Reset") { _, _ ->
                resetSelectedCourses(courses) // Eksekusi reset
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    private fun showResetAllConfirmation() {
        // Konfirmasi reset semua progress user
        AlertDialog.Builder(requireContext())
            .setTitle("Reset Semua Progress")
            .setMessage("Apakah Anda yakin ingin mereset semua progress? Semua kemajuan belajar Anda akan hilang.")
            .setPositiveButton("Ya, Reset Semua") { _, _ ->
                resetAllProgress() // Reset seluruh progress
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    private fun resetSelectedCourses(courses: List<MycourseModel>) {
        // Reset progress berdasarkan title (unik)
        courses.forEach { course ->
            CourseProgressStorage.resetCourseProgress(requireContext(), course.title)
        }

        // Update UI setelah reset
        refreshCourseProgress()
        adapter.notifyDataSetChanged()

        Toast.makeText(requireContext(),
            "${courses.size} course berhasil direset", Toast.LENGTH_SHORT).show()
    }

    private fun resetAllProgress() {
        CourseProgressStorage.resetAllProgress(requireContext()) // Hapus semua progress

        // Refresh UI
        refreshCourseProgress()
        adapter.notifyDataSetChanged()

        Toast.makeText(requireContext(),
            "Semua progress berhasil direset", Toast.LENGTH_SHORT).show()
    }

    // ------------------------------ SEARCH ------------------------------ //

    private fun filterData(keyword: String) {
        // Filter kursus sesuai keyword
        val filteredList =
            if (keyword.isEmpty()) listCourse // Jika kosong → tampilkan semua
            else listCourse.filter {
                it.title.contains(keyword, ignoreCase = true) // Cari berdasarkan judul
            }

        adapter.updateList(ArrayList(filteredList)) // Update tampilan list
    }

    // ------------------------------ DATA ------------------------------ //

    private fun loadData() {
        // Mengisi list course manual (statik)
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
