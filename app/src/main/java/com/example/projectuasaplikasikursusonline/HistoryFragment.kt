package com.example.projectuasaplikasikursusonline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectuasaplikasikursusonline.storage.HistoryStorage
import com.example.projectuasaplikasikursusonline.R

class HistoryFragment : Fragment() {

    // Deklarasi RecyclerView untuk menampilkan daftar history
    private lateinit var recyclerView: RecyclerView

    // Deklarasi adapter untuk menghubungkan data ke RecyclerView
    private lateinit var adapter: HistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Menghubungkan fragment dengan layout fragment_history.xml
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Mengambil RecyclerView dari layout menggunakan ID rvHistory
        recyclerView = view.findViewById(R.id.rvHistory)

        // Mengatur layout RecyclerView menjadi list vertikal
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Mengambil data history yang disimpan sebelumnya dari HistoryStorage
        val dataHistory = HistoryStorage.getHistory(requireContext())

        // Membuat adapter dengan data history
        adapter = HistoryAdapter(dataHistory)

        // Menghubungkan adapter ke RecyclerView
        recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()

        // Mengambil data history terbaru ketika fragment kembali aktif
        val updatedHistory = HistoryStorage.getHistory(requireContext())

        // Membuat adapter baru dengan data yang sudah diperbarui
        adapter = HistoryAdapter(updatedHistory)

        // Mengatur kembali adapter ke RecyclerView untuk me-refresh tampilan
        recyclerView.adapter = adapter
    }
}
