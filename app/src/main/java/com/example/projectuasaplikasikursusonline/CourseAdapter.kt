package com.example.projectuasaplikasikursusonline
// → Package utama untuk file CourseAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectuasaplikasikursusonline.R
// → Import semua komponen UI & RecyclerView yang dibutuhkan.


class CourseAdapter(
    private var courseList: List<Course>,       // List data course yang akan ditampilkan
    private val onItemClick: (Course) -> Unit   // Callback ketika tombol "Detail" diklik
) : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {
    // → Adapter untuk menghubungkan data Course ke item layout RecyclerView.


    // ============================================================
    //                    VIEW HOLDER
    // ============================================================
    inner class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // → Menampung semua view di dalam item_course.xml

        val imgCourse: ImageView = itemView.findViewById(R.id.imgCourse) // Gambar course
        val txtTitle: TextView = itemView.findViewById(R.id.txtTitle)     // Judul course
        val txtPrice: TextView = itemView.findViewById(R.id.txtPrice)     // Harga course
        val btnDetail: Button = itemView.findViewById(R.id.btnDetail)     // Tombol detail
    }


    // ============================================================
    //   MEMBUAT ITEM LAYOUT UNTUK SETIAP BARIS DI RECYCLERVIEW
    // ============================================================
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        // → Menghubungkan item_course.xml menjadi bentuk View

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_course, parent, false)
        // → Inflate layout item untuk RecyclerView

        return CourseViewHolder(view)
        // → Kembalikan ViewHolder yang sudah berisi view item
    }


    // ============================================================
    //   MENGISI DATA KE DALAM ITEM RECYCLERVIEW
    // ============================================================
    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val item = courseList[position]     // Ambil data berdasarkan posisi list

        holder.imgCourse.setImageResource(item.imageRes)  // Set gambar course
        holder.txtTitle.text = item.title                 // Set judul
        holder.txtPrice.text = item.price                 // Set harga

        holder.btnDetail.setOnClickListener {
            onItemClick(item)     // Jalankan callback ketika tombol ditekan
        }
    }


    // ============================================================
    //       MENGATUR JUMLAH DATA YANG AKAN DITAMPILKAN
    // ============================================================
    override fun getItemCount(): Int = courseList.size
    // → Jumlah item yang tampil = ukuran list course


    // ============================================================
    //           FITUR FILTER PENCARIAN DI HOME
    // ============================================================
    fun filterList(filteredList: List<Course>) {
        courseList = filteredList           // Update list dengan list hasil filter
        notifyDataSetChanged()              // Refresh RecyclerView
    }
}
