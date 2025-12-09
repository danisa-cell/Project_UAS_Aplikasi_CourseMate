package com.example.projectuasaplikasikursusonline.storage

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object HistoryStorage {

    private const val PREF_NAME = "history_pref"
    private const val KEY_HISTORY = "history_data"

    // Ambil seluruh history
    fun getHistory(context: Context): MutableList<HistoryModel> {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(KEY_HISTORY, null)

        return if (json != null) {
            val type = object : TypeToken<MutableList<HistoryModel>>() {}.type
            Gson().fromJson(json, type)
        } else {
            mutableListOf()
        }
    }

    // Tambahkan history baru (di paling atas)
    fun addHistory(context: Context, item: HistoryModel) {
        val list = getHistory(context)
        list.add(0, item)
        save(context, list)
    }

    // Update status history TERBARU
    fun updateLastStatus(context: Context, newStatus: String) {
        val list = getHistory(context)
        if (list.isNotEmpty()) {
            list[0].status = newStatus
            save(context, list)
        }
    }

    // Simpan ke SharedPreferences
    private fun save(context: Context, list: MutableList<HistoryModel>) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_HISTORY, Gson().toJson(list)).apply()
    }
}
