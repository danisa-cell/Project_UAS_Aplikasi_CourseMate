package com.example.projectuasaplikasikursusonline

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectuasaplikasikursusonline.storage.CourseProgressStorage

class MycourseAdapter(
    private var list: List<MycourseModel>,
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
        val context = holder.itemView.context

        // ✅ Ambil progress dari storage pakai TITLE
        val totalProgress = CourseProgressStorage.getTotalProgress(
            context,
            item.title  // ← PENTING: Pakai title, bukan id
        )

        holder.tvTitle.text = item.title
        holder.img.setImageResource(item.imageRes)

        holder.tvProgress.text = "$totalProgress%"
        holder.progressBar.progress = totalProgress

        holder.btnNext.setOnClickListener {
            onClick(item)
        }
    }

    override fun getItemCount(): Int = list.size

    fun updateList(newList: List<MycourseModel>) {
        list = newList
        notifyDataSetChanged()
    }
}
