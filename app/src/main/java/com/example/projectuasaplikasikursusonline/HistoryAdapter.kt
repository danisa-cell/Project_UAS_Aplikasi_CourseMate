package com.example.projectuasaplikasikursusonline

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectuasaplikasikursusonline.storage.HistoryModel
import com.example.projectuasaplikasikursusonline.R

// Adapter untuk menampilkan daftar history ke dalam RecyclerView
class HistoryAdapter(
    private val items: List<HistoryModel>   // List data history yang akan ditampilkan
) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    // ViewHolder berfungsi menampung view pada item_history.xml
    class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        // Mengambil komponen tampilan dari layout item history
        val img: ImageView = view.findViewById(R.id.imgHistory)
        val title: TextView = view.findViewById(R.id.txtTitle)
        val date: TextView = view.findViewById(R.id.txtDate)
        val status: TextView = view.findViewById(R.id.txtStatus)
    }

    // Membuat tampilan setiap item list menggunakan layout item_history.xml
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {

        // Inflate layout item_history menjadi tampilan item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_history, parent, false)

        // Mengembalikan ViewHolder yang berisi tampilan tersebut
        return HistoryViewHolder(view)
    }

    // Menghubungkan data dengan view pada setiap posisi item
    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {

        // Mengambil data sesuai posisi item
        val item = items[position]

        // Menampilkan gambar, judul, tanggal, dan status ke tampilan
        holder.img.setImageResource(item.image)
        holder.title.text = item.title
        holder.date.text = item.date
        holder.status.text = item.status

        // Membersihkan teks status agar lebih mudah dicocokkan warnanya
        val cleanStatus = item.status
            .replace("Status", "")   // hapus kata "Status"
            .replace(":", "")        // hapus titik dua
            .trim()                  // hilangkan spasi
            .lowercase()             // ubah menjadi huruf kecil

        // Mengatur warna teks status berdasarkan isi status
        when (cleanStatus) {
            "berhasil" -> holder.status.setTextColor(
                holder.itemView.context.getColor(R.color.green)   // warna hijau
            )
            "menunggu pembayaran" -> holder.status.setTextColor(
                holder.itemView.context.getColor(R.color.yellow)  // warna kuning
            )
            "kadaluarsa" -> holder.status.setTextColor(
                holder.itemView.context.getColor(R.color.red)     // warna merah
            )
            "pembayaran via admin" -> holder.status.setTextColor(
                holder.itemView.context.getColor(R.color.blue_dark) // warna biru tua
            )
            else -> holder.status.setTextColor(
                holder.itemView.context.getColor(R.color.black)   // default: hitam
            )
        }
    }

    // Mengembalikan jumlah item dalam daftar history
    override fun getItemCount(): Int = items.size
}
