import java.io.Serializable   // Import interface Serializable agar objek bisa dikirim lewat Intent

// ==============================================
// DATA CLASS Quiz
// ==============================================
// - Representasi 1 soal quiz
// - Berisi teks soal, daftar pilihan, dan jawaban benar
// - Menggunakan Serializable agar bisa dipassing
//   antar Activity lewat Intent
// ==============================================
data class Quiz(

    // Teks pertanyaan
    val question: String,

    // List pilihan jawaban (pakai ArrayList agar mudah diproses dan kompatibel dengan Intent)
    val options: ArrayList<String>,

    // Index jawaban benar (0 = pilihan pertama, 1 = pilihan kedua, dst.)
    val correctIndex: Int

// Serializable membuat objek Quiz bisa “dipaketkan” menjadi data
// sehingga dapat dikirim ke activity lain melalui intent.putExtra()
) : Serializable
