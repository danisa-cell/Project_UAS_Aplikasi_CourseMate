package com.example.projectuasaplikasikursusonline

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.example.projectuasaplikasikursusonline.storage.HistoryModel
import com.example.projectuasaplikasikursusonline.storage.HistoryStorage
import java.text.SimpleDateFormat
import java.util.*

class PaymentFragment : Fragment() {

    private lateinit var txtQty: TextView
    private lateinit var txtSubtotal: TextView
    private lateinit var txtTotal: TextView
    private lateinit var txtTitle: TextView
    private lateinit var txtPrice: TextView
    private lateinit var txtTutor: TextView
    private lateinit var imgCourse: ImageView

    private var qty = 1
    private var price = 150000

    private var statusFinalized = false // mencegah double update

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_payment, container, false)

        val btnMinus = view.findViewById<ImageButton>(R.id.btnMinus)
        val btnPlus = view.findViewById<ImageButton>(R.id.btnPlus)
        val btnPayNow = view.findViewById<Button>(R.id.btnPayNow)
        val btnBack = view.findViewById<ImageView>(R.id.btnBackPayment)

        txtQty = view.findViewById(R.id.txtQty)
        txtSubtotal = view.findViewById(R.id.txtSubtotal)
        txtTotal = view.findViewById(R.id.txtTotalPayment)
        txtTitle = view.findViewById(R.id.txtPaymentTitle)
        txtPrice = view.findViewById(R.id.txtPaymentPrice)
        txtTutor = view.findViewById(R.id.txtPaymentTutor)
        imgCourse = view.findViewById(R.id.imgCoursePayment)

        // Ambil data dari fragment sebelumnya
        val title = arguments?.getString("title") ?: ""
        val priceText = arguments?.getString("price") ?: "150000"
        val tutorName = arguments?.getString("tutorName") ?: ""
        val imageRes = arguments?.getInt("imageRes") ?: 0

        txtTitle.text = title
        txtTutor.text = tutorName

        price = priceText.replace("Rp", "")
            .replace(".", "")
            .replace(" ", "")
            .toIntOrNull() ?: 150000

        txtPrice.text = "Rp. ${formatRupiah(price)}"
        if (imageRes != 0) imgCourse.setImageResource(imageRes)

        updatePrice()

        // ==============================================
        // BUAT HISTORY PERTAMA: MENUNGGU PEMBAYARAN
        // ==============================================
        HistoryStorage.addHistory(
            requireContext(),
            HistoryModel(
                title = "Kursus : $title",
                date = "Tanggal : ${getTodayDate()}",
                status = "Status : menunggu pembayaran",
                image = imageRes
            )
        )

        // Tombol qty
        btnMinus.setOnClickListener {
            if (qty > 1) {
                qty--
                updatePrice()
            }
        }

        btnPlus.setOnClickListener {
            qty++
            updatePrice()
        }

        // Tombol back → dianggap batal
        btnBack.setOnClickListener {
            setKadaluarsa()
            findNavController().navigateUp()
        }

        btnPayNow.setOnClickListener {
            showPaymentOptions()
        }

        return view
    }

    private fun updatePrice() {
        val total = qty * price
        txtQty.text = qty.toString()
        txtSubtotal.text = "Rp. ${formatRupiah(total)}"
        txtTotal.text = "Rp. ${formatRupiah(total)}"
    }

    private fun formatRupiah(value: Int): String {
        return String.format("%,d", value).replace(',', '.')
    }

    private fun showPaymentOptions() {
        val options = arrayOf(
            "Bayar langsung ke tutor",
            "Transfer ke admin (WhatsApp)"
        )

        AlertDialog.Builder(requireContext())
            .setTitle("Pilih Metode Pembayaran")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> bayarKeTutor()
                    1 -> bayarViaWhatsApp()
                }
            }
            .show()
    }

    private fun bayarKeTutor() {
        AlertDialog.Builder(requireContext())
            .setTitle("Pembayaran")
            .setMessage("Silakan lakukan pembayaran langsung ke tutor.")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()

                setBerhasil()

                findNavController().navigate(R.id.paymentSuccessFragment)
            }
            .show()
    }

    private fun bayarViaWhatsApp() {

        setPembayaranAdmin()

        val phoneNumber = "6285380347744"
        val message = "Halo admin, saya ingin transfer pembayaran kursus."
        val url = "https://wa.me/$phoneNumber?text=${Uri.encode(message)}"

        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(requireContext(), "WhatsApp tidak terpasang", Toast.LENGTH_SHORT).show()
        }
    }

    // ============================
    // STATUS HANDLER
    // ============================

    private fun setBerhasil() {
        if (statusFinalized) return
        statusFinalized = true

        HistoryStorage.updateLastStatus(
            requireContext(),
            "Status : berhasil"
        )
    }

    private fun setPembayaranAdmin() {
        if (statusFinalized) return
        statusFinalized = true

        HistoryStorage.updateLastStatus(
            requireContext(),
            "Status : pembayaran via admin"
        )
    }

    private fun setKadaluarsa() {
        if (statusFinalized) return
        statusFinalized = true

        HistoryStorage.updateLastStatus(
            requireContext(),
            "Status : kadaluarsa"
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        setKadaluarsa()
    }

    private fun getTodayDate(): String {
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
        return dateFormat.format(Date())
    }
}
