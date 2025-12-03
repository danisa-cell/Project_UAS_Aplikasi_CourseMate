package com.example.projectuasaplikasikursusonline

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MycourseAdapter(
    private val list: List<MycourseModel>
) : RecyclerView.Adapter<MycourseAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvProgress: TextView = view.findViewById(R.id.tvProgress)
        val btnStart: Button = view.findViewById(R.id.btnStart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_mycourse, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.tvTitle.text = item.title
        holder.tvProgress.text = "${item.progress}%"
        holder.btnStart.text = if (item.progress == 0) "Start" else "Next"
    }

    override fun getItemCount(): Int = list.size
}
