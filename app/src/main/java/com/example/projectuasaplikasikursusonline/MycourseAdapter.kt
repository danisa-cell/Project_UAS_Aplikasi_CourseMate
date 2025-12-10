package com.example.projectuasaplikasikursusonline
// Package utama tempat adapter disimpan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectuasaplikasikursusonline.storage.CourseProgressStorage
import com.example.projectuasaplikasikursusonline.R
// Import komponen yang dipakai dalam adapter

class MycourseAdapter(
    private var list: List<MycourseModel>,        // Menyimpan data list course
    private val onClick: (MycourseModel) -> Unit  // Callback ketika tombol Next ditekan
) : RecyclerView.Adapter<MycourseAdapter.ViewHolder>() {

    // ViewHolder menyimpan referensi view dalam satu item list
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.findViewById(R.id.imgCourse)           // Gambar course
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)          // Judul course
        val tvProgress: TextView = view.findViewById(R.id.tvProgress)    // Progress dalam angka (%)
        val progressBar: ProgressBar = view.findViewById(R.id.progressBar) // ProgressBar visual
        val btnNext: Button = view.findViewById(R.id.btnNext)            // Tombol menuju detail
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Membuat layout item dari XML item_mycourse
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_mycourse, parent, false)
        return ViewHolder(view) // Mengembalikan ViewHolder yang sudah siap dipakai
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]               // Mengambil data course berdasarkan posisi list
        val context = holder.itemView.context   // Mengambil context dari item view

        // Mengambil total progress dari SharedPreferences berdasarkan title course
        val totalProgress = CourseProgressStorage.getTotalProgress(
            context,
            item.title
        )

        holder.tvTitle.text = item.title               // Menampilkan judul course
        holder.img.setImageResource(item.imageRes)     // Menampilkan gambar dari resource

        holder.tvProgress.text = "$totalProgress%"     // Menampilkan progress dalam teks
        holder.progressBar.progress = totalProgress    // Menampilkan progress pada bar

        // Memberikan aksi ketika tombol Next ditekan
        holder.btnNext.setOnClickListener {
            onClick(item)  // Mengirim data course ke callback fragment/activity
        }
    }

    override fun getItemCount(): Int = list.size
    // Mengembalikan jumlah item di dalam list

    fun updateList(newList: List<MycourseModel>) {
        list = newList           // Update data list baru
        notifyDataSetChanged()   // Memberi tahu RecyclerView agar tampilannya diperbarui
    }
}
