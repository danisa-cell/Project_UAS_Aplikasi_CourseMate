package com.example.projectuasaplikasikursusonline

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectuasaplikasikursusonline.R


class CourseAdapter(
    private var courseList: List<Course>,
    private val onItemClick: (Course) -> Unit
) : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    inner class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgCourse: ImageView = itemView.findViewById(R.id.imgCourse)
        val txtTitle: TextView = itemView.findViewById(R.id.txtTitle)
        val txtPrice: TextView = itemView.findViewById(R.id.txtPrice)
        val btnDetail: Button = itemView.findViewById(R.id.btnDetail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_course, parent, false)
        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val item = courseList[position]

        holder.imgCourse.setImageResource(item.imageRes)
        holder.txtTitle.text = item.title
        holder.txtPrice.text = item.price

        holder.btnDetail.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount(): Int = courseList.size

    fun filterList(filteredList: List<Course>) {
        courseList = filteredList
        notifyDataSetChanged()
    }
}
