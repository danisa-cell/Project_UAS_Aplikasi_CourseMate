package com.example.projectuasaplikasikursusonline.storage

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// Object singleton: hanya ada satu instance untuk menyimpan & membaca history
object HistoryStorage {

    // Nama file SharedPreferences
    private const val PREF_NAME = "history_pref"

    // Key untuk menyimpan data history dalam bentuk JSON
    private const val KEY_HISTORY = "history_data"

    // ================================
    // FUNGSI AMBIL SELURUH HISTORY
    // ================================
    fun getHistory(context: Context): MutableList<HistoryModel> {

        // Akses SharedPreferences berdasarkan nama "history_pref"
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        // Ambil string JSON yang pernah disimpan
        val json = prefs.getString(KEY_HISTORY, null)

        // Jika ada JSON tersimpan → ubah kembali jadi list HistoryModel
        return if (json != null) {
            // TypeToken untuk mengubah JSON menjadi MutableList<HistoryModel>
            val type = object : TypeToken<MutableList<HistoryModel>>() {}.type
            Gson().fromJson(json, type)  // Convert JSON ke List
        } else {
            // Jika belum ada data → kembalikan list kosong
            mutableListOf()
        }
    }

    // ==============================================
    // MENAMBAHKAN HISTORY BARU DI URUTAN PALING ATAS
    // ==============================================
    fun addHistory(context: Context, item: HistoryModel) {

        // Ambil list history lama
        val list = getHistory(context)

        // Tambahkan item baru di posisi index 0 (paling atas)
        list.add(0, item)

        // Simpan kembali list yang sudah diperbarui
        save(context, list)
    }

    // ======================================================
    // UPDATE STATUS DATA HISTORY TERBARU (ITEM INDEX 0)
    // ======================================================
    fun updateLastStatus(context: Context, newStatus: String) {

        // Ambil data history
        val list = getHistory(context)

        // Cek apakah list tidak kosong
        if (list.isNotEmpty()) {

            // Update hanya STATUS item pertama (index 0)
            list[0].status = newStatus

            // Simpan kembali hasil update
            save(context, list)
        }
    }

    // ======================================================
    // FUNGSI UNTUK MENYIMPAN LIST HISTORY KE PREFERENCES
    // ======================================================
    private fun save(context: Context, list: MutableList<HistoryModel>) {

        // Akses SharedPreferences
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        // Convert list menjadi JSON dan simpan
        prefs.edit()
            .putString(KEY_HISTORY, Gson().toJson(list))
            .apply()
    }
}
