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

    class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.findViewById(R.id.imgHistory)
        val title: TextView = view.findViewById(R.id.txtTitle)
        val date: TextView = view.findViewById(R.id.txtDate)
        val status: TextView = view.findViewById(R.id.txtStatus)
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

        // Ambil status sebenarnya tanpa "Status :"
        val cleanStatus = item.status
            .replace("Status", "")
            .replace(":", "")
            .trim()
            .lowercase()

        // ====== SET WARNA STATUS ======
        when (cleanStatus) {
            "berhasil" -> holder.status.setTextColor(
                holder.itemView.context.getColor(R.color.green)
            )

            "menunggu pembayaran" -> holder.status.setTextColor(
                holder.itemView.context.getColor(R.color.yellow)
            )

            "kadaluarsa" -> holder.status.setTextColor(
                holder.itemView.context.getColor(R.color.red)
            )

            else -> holder.status.setTextColor(
                holder.itemView.context.getColor(R.color.black)
            )
        }
    }

    override fun getItemCount(): Int = items.size
}
