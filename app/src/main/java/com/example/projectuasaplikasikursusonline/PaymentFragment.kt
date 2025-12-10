package com.example.projectuasaplikasikursusonline
// Package tempat file ini berada

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
import com.example.projectuasaplikasikursusonline.R
import java.text.SimpleDateFormat
import java.util.*

class PaymentFragment : Fragment() {

    // Deklarasi komponen UI (diisi nanti saat onCreateView)
    private lateinit var txtQty: TextView
    private lateinit var txtSubtotal: TextView
    private lateinit var txtTotal: TextView
    private lateinit var txtTitle: TextView
    private lateinit var txtPrice: TextView
    private lateinit var txtTutor: TextView
    private lateinit var imgCourse: ImageView

    // Nilai awal jumlah dan harga
    private var qty = 1
    private var price = 150000

    // Mencegah status diubah 2 kali
    private var statusFinalized = false

    // Dipanggil ketika fragment menampilkan layout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Menghubungkan fragment dengan layout XML
        val view = inflater.inflate(R.layout.fragment_payment, container, false)

        // Menghubungkan semua tombol & text menggunakan ID dari XML
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

        // Mengambil data yang dikirim dari DetailFragment
        val title = arguments?.getString("title") ?: ""
        val priceText = arguments?.getString("price") ?: "150000"
        val tutorName = arguments?.getString("tutorName") ?: ""
        val imageRes = arguments?.getInt("imageRes") ?: 0

        // Menampilkan data ke UI
        txtTitle.text = title
        txtTutor.text = tutorName

        // Mengubah harga string menjadi integer
        price = priceText.replace("Rp", "")
            .replace(".", "")
            .replace(" ", "")
            .toIntOrNull() ?: 150000

        txtPrice.text = "Rp. ${formatRupiah(price)}"

        // Jika ada gambar course, tampilkan di ImageView
        if (imageRes != 0) imgCourse.setImageResource(imageRes)

        // Update harga total pertama kali
        updatePrice()

        // Membuat history awal "menunggu pembayaran"
        HistoryStorage.addHistory(
            requireContext(),
            HistoryModel(
                title = "Kursus : $title",
                date = "Tanggal : ${getTodayDate()}",
                status = "Status : menunggu pembayaran",
                image = imageRes
            )
        )

        // Ketika tombol minus ditekan → kurangi qty
        btnMinus.setOnClickListener {
            if (qty > 1) {
                qty--
                updatePrice()
            }
        }

        // Ketika tombol plus ditekan → tambah qty
        btnPlus.setOnClickListener {
            qty++
            updatePrice()
        }

        // Tombol back → dianggap batal (kadaluarsa)
        btnBack.setOnClickListener {
            setKadaluarsa()
            findNavController().navigateUp()
        }

        // Tombol "Bayar Sekarang"
        btnPayNow.setOnClickListener {
            showPaymentOptions()
        }

        return view
    }

    // Menghitung ulang harga & menampilkan ke UI
    private fun updatePrice() {
        val total = qty * price
        txtQty.text = qty.toString()
        txtSubtotal.text = "Rp. ${formatRupiah(total)}"
        txtTotal.text = "Rp. ${formatRupiah(total)}"
    }

    // Format angka ke bentuk rupiah
    private fun formatRupiah(value: Int): String {
        return String.format("%,d", value).replace(',', '.')
    }

    // Menampilkan pilihan metode pembayaran
    private fun showPaymentOptions() {
        val options = arrayOf(
            "Bayar langsung ke tutor",
            "Transfer ke admin (WhatsApp)"
        )

        AlertDialog.Builder(requireContext())
            .setTitle("Pilih Metode Pembayaran")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> bayarKeTutor()        // Jika pilih opsi 1
                    1 -> bayarViaWhatsApp()    // Jika pilih opsi 2
                }
            }
            .show()
    }

    // Jika user memilih "Bayar langsung ke tutor"
    private fun bayarKeTutor() {
        AlertDialog.Builder(requireContext())
            .setTitle("Pembayaran")
            .setMessage("Silakan lakukan pembayaran langsung ke tutor.")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()

                // Set status history → berhasil
                setBerhasil()

                // Pindah ke halaman sukses
                findNavController().navigate(R.id.paymentSuccessFragment)
            }
            .show()
    }

    // Jika user memilih "Transfer ke admin"
    private fun bayarViaWhatsApp() {

        // Set status → pembayaran via admin
        setPembayaranAdmin()

        // Nomor admin
        val phoneNumber = "62895380347744"

        // Pesan awal
        val message = "Halo admin, saya ingin transfer pembayaran kursus."

        // URL untuk membuka WhatsApp
        val url = "https://wa.me/$phoneNumber?text=${Uri.encode(message)}"

        try {
            // Coba buka WhatsApp
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        } catch (e: ActivityNotFoundException) {
            // Kalau tidak ada WhatsApp
            Toast.makeText(requireContext(), "WhatsApp tidak terpasang", Toast.LENGTH_SHORT).show()
        }
    }

    // Set status menjadi "berhasil"
    private fun setBerhasil() {
        if (statusFinalized) return        // Mencegah double set
        statusFinalized = true

        HistoryStorage.updateLastStatus(
            requireContext(),
            "Status : berhasil"
        )
    }

    // Set status menjadi "pembayaran via admin"
    private fun setPembayaranAdmin() {
        if (statusFinalized) return
        statusFinalized = true

        HistoryStorage.updateLastStatus(
            requireContext(),
            "Status : pembayaran via admin"
        )
    }

    // Set status menjadi "kadaluarsa"
    private fun setKadaluarsa() {
        if (statusFinalized) return
        statusFinalized = true

        HistoryStorage.updateLastStatus(
            requireContext(),
            "Status : kadaluarsa"
        )
    }

    // Jika fragment dihancurkan (back atau pindah), otomatis set kadaluarsa
    override fun onDestroy() {
        super.onDestroy()
        setKadaluarsa()
    }

    // Mendapatkan tanggal hari ini
    private fun getTodayDate(): String {
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
        return dateFormat.format(Date())
    }
}
