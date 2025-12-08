package com.example.projectuasaplikasikursusonline

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MycourseAdapter(
    private var list: List<MycourseModel>,   // ✅ jadi var
    private val onClick: (MycourseModel) -> Unit
) : RecyclerView.Adapter<MycourseAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.findViewById(R.id.imgCourse)
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvProgress: TextView = view.findViewById(R.id.tvProgress)
        val progressBar: ProgressBar = view.findViewById(R.id.progressBar)
        val btnNext: Button = view.findViewById(R.id.btnNext)
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
        holder.progressBar.progress = item.progress

        holder.img.setImageResource(item.imageRes)

        holder.btnNext.setOnClickListener {
            onClick(item)
        }
    }

    override fun getItemCount(): Int = list.size

    // ✅ fungsi buat update data saat search
    fun updateList(newList: List<MycourseModel>) {
        list = newList
        notifyDataSetChanged()
    }
}
