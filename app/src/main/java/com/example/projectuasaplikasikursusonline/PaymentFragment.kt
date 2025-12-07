package com.example.projectuasaplikasikursusonline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.text.NumberFormat
import java.util.Locale

class PaymentFragment : Fragment() {

    private var quantity = 1
    private var price = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_payment, container, false)

        // ===== Tombol Back =====
        val btnBack: ImageView = view.findViewById(R.id.btnBackPayment)
        btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        // ===== Ambil data dari bundle =====
        val courseImage = arguments?.getInt("imageRes") ?: 0
        val title = arguments?.getString("title") ?: ""
        val tutorName = arguments?.getString("tutorName") ?: ""
        val tutorImage = arguments?.getInt("tutorImage") ?: 0
        val priceStr = arguments?.getString("price") ?: "0"

        // Bersihkan format harga "Rp 200.000"
        price = priceStr.replace("Rp", "")
            .replace(".", "")
            .replace(",", "")
            .trim()
            .toInt()

        // ===== Hubungkan UI =====
        val imgCourse: ImageView = view.findViewById(R.id.imgCoursePayment)
        val txtTitle: TextView = view.findViewById(R.id.txtPaymentTitle)
        val txtTutor: TextView = view.findViewById(R.id.txtPaymentTutor)
        val txtPrice: TextView = view.findViewById(R.id.txtPaymentPrice)

        val txtQty: TextView = view.findViewById(R.id.txtQty)
        val btnPlus: ImageButton = view.findViewById(R.id.btnPlus)
        val btnMinus: ImageButton = view.findViewById(R.id.btnMinus)

        val txtSubtotal: TextView = view.findViewById(R.id.txtSubtotal)
        val txtTotal: TextView = view.findViewById(R.id.txtTotalPayment)

        // Set course data
        imgCourse.setImageResource(courseImage)
        txtTitle.text = title
        txtTutor.text = tutorName
        txtPrice.text = formatRupiah(price)

        // ===== Fungsi update harga =====
        fun updatePrice() {
            val subtotal = quantity * price

            txtSubtotal.text = formatRupiah(subtotal)
            txtTotal.text = formatRupiah(subtotal)
            txtQty.text = quantity.toString()
        }

        updatePrice()

        // ===== Tombol + =====
        btnPlus.setOnClickListener {
            quantity++
            updatePrice()
        }

        // ===== Tombol - (minimal 1) =====
        btnMinus.setOnClickListener {
            if (quantity > 1) {
                quantity--
                updatePrice()
            }
        }

        return view
    }

    // ===== Format Rupiah =====
    private fun formatRupiah(value: Int): String {
        val formatter = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
        return formatter.format(value).replace(",00", "")
    }
}
