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

        val dataHistory = HistoryStorage.getHistory(requireContext())

        adapter = HistoryAdapter(dataHistory)
        recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()

        // refresh otomatis setelah balik dari Payment
        val updatedHistory = HistoryStorage.getHistory(requireContext())
        adapter = HistoryAdapter(updatedHistory)
        recyclerView.adapter = adapter
    }
}
