package com.example.projectuasaplikasikursusonline

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoryAdapter(
    private val items: List<HistoryModel>
) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
 // tes kontribusi
    class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img = view.findViewById<ImageView>(R.id.imgHistory)
        val title = view.findViewById<TextView>(R.id.txtTitle)
        val date = view.findViewById<TextView>(R.id.txtDate)
        val status = view.findViewById<TextView>(R.id.txtStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_history, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = items[position]
        holder.img.setImageResource(item.image)
        holder.title.text = item.title
        holder.date.text = item.date
        holder.status.text = item.status
    }

    override fun getItemCount(): Int = items.size
}
