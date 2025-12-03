package com.example.projectuasaplikasikursusonline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistoryFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.rvHistory)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val dataHistory = listOf(
            HistoryModel(
                title = "Kursus : Java",
                date = "Tanggal : 11 November 2025",
                status = "Status : Menunggu pembayaran",
                image = R.drawable.ic_java
            ),
            HistoryModel(
                title = "Kursus : UI/UX Design",
                date = "Tanggal : 12 November 2025",
                status = "Status : Berhasil",
                image = R.drawable.ic_uiux
            ),
            HistoryModel(
                title = "Kursus : Python",
                date = "Tanggal : 10 September 2025",
                status = "Status : Berhasil",
                image = R.drawable.ic_python
            ),
            HistoryModel(
                title = "Kursus : Android Development",
                date = "Tanggal : 01 September 2025",
                status = "Status : Berhasil",
                image = R.drawable.ic_android
            ),
            HistoryModel(
                title = "Kursus : Data Analytics",
                date = "Tanggal : 17 Agustus 2025",
                status = "Status : Kadaluarsa",
                image = R.drawable.ic_data
            ),
            HistoryModel(
                title = "Kursus : Web Programing",
                date = "Tanggal : 05 Juni 2025",
                status = "Status : Kadaluarsa",
                image = R.drawable.ic_web
            )
        )

        adapter = HistoryAdapter(dataHistory)
        recyclerView.adapter = adapter
    }
}
