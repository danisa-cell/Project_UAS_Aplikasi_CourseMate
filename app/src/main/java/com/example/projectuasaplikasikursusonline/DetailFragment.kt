package com.example.projectuasaplikasikursusonline

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.projectuasaplikasikursusonline.R

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,     // Digunakan untuk mengubah layout XML ke View
        container: ViewGroup?,        // Parent view tempat fragment ditempatkan
        savedInstanceState: Bundle?   // Menyimpan state jika ada
    ): View? {

        // Mengubah layout fragment_detail.xml menjadi View
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        // ----------------------- Tombol Back -----------------------
        val btnBack: ImageView = view.findViewById(R.id.btnBack)

        // Aksi ketika tombol back ditekan -> kembali ke fragment sebelumnya
        btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        // ----------------------- Ambil Data dari Bundle -----------------------

        // Thumbnail course (image thumbnail)
        val imageRes = arguments?.getInt("imageRes") ?: 0

        // Judul course
        val title = arguments?.getString("title") ?: ""

        // Nama tutor
        val tutorName = arguments?.getString("tutorName") ?: ""

        // Foto tutor
        val tutorImage = arguments?.getInt("tutorImage") ?: 0

        // Deskripsi course
        val description = arguments?.getString("description") ?: ""

        // ----------------------- Handling Harga -----------------------

        // Ambil harga dalam bentuk String jika ada
        var price = arguments?.getString("price")

        // Jika harga kosong atau null, coba ambil harga dalam bentuk Int
        if (price.isNullOrEmpty()) {
            val priceInt = arguments?.getInt("price") ?: 0
            price = "Rp $priceInt"
        }

        // Harga final; jika masih null atau kosong, set default
        if (price.isNullOrEmpty()) price = "Rp 0"

        // ----------------------- Ambil View dari XML -----------------------

        val imgCourse: ImageView = view.findViewById(R.id.imgThumbnail)
        val txtTitle: TextView = view.findViewById(R.id.txtTitle)
        val txtTutor: TextView = view.findViewById(R.id.txtTutor)
        val imgTutor: ImageView = view.findViewById(R.id.imgTutor)
        val txtDesc: TextView = view.findViewById(R.id.txtDescription)

        // ----------------------- Set Data ke View -----------------------

        imgCourse.setImageResource(imageRes)   // Set thumbnail
        txtTitle.text = title                  // Set judul
        txtTutor.text = tutorName              // Set nama tutor
        imgTutor.setImageResource(tutorImage)  // Set foto tutor
        txtDesc.text = description             // Set deskripsi

        // ----------------------- Tombol Pesan Sekarang -----------------------

        val btnPesan: Button = view.findViewById(R.id.btnPesan)

        // Ketika tombol pesan ditekan -> pindah ke PaymentFragment sambil mengirim data
        btnPesan.setOnClickListener {

            // Buat bundle untuk mengirim data
            val bundle = Bundle().apply {
                putInt("imageRes", imageRes)
                putString("title", title)
                putString("tutorName", tutorName)
                putString("price", price)
            }

            // Navigasi ke PaymentFragment
            findNavController().navigate(
                R.id.action_detailFragment_to_paymentFragment,
                bundle
            )
        }

        // Mengembalikan View yang sudah siap ditampilkan
        return view
    }
}
