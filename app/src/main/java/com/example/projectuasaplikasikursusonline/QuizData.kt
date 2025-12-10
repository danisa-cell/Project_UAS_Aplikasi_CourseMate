package com.example.projectuasaplikasikursusonline
// Package tempat file ini berada. Digunakan untuk mengelompokkan class dalam proyek Android.

import java.io.Serializable
// Mengimpor interface Serializable agar objek Quiz bisa dikirim lewat Intent ke Activity lain.


// ==========================================================
// DATA CLASS QUIZ
// ==========================================================

data class Quiz(
    val question: String,        // Menyimpan teks soal / pertanyaan.
    val options: List<String>,   // Menyimpan daftar jawaban pilihan ganda (A–E).
    val correctIndex: Int        // Menyimpan index jawaban yang benar (0–4).
) : Serializable  // ⬅️ Wajib! Agar object Quiz dapat dipaketkan dan dikirim melalui Intent.
// Tanpa Serializable, aplikasi akan crash ketika mengirim ArrayList<Quiz>.


//// ==========================================================
// OBJECT QUIZDATA — TEMPAT SEMUA SOAL DISIMPAN
// ==========================================================

object QuizData {
    // Object (singleton) → hanya dibuat sekali selama aplikasi berjalan.
    // Menyimpan semua question yang digunakan dalam quiz.

    val questions = listOf(
        // listOf() → membuat list soal (immutable).

        // =======================
        // 1. SOAL PERTAMA
        // =======================
        Quiz(
            "Apa fungsi utama bahasa Kotlin dalam pengembangan aplikasi Android modern?",
            // Teks soal

            listOf(
                // Daftar pilihan jawaban
                "Sebagai bahasa markup untuk mendesain tampilan aplikasi Android.",
                "Sebagai bahasa utama untuk menulis logika program dan mengelola fungsionalitas aplikasi.",
                "Sebagai tool otomatis yang menghasilkan file layout XML.",
                "Sebagai sistem penyimpanan berbasis cloud untuk aplikasi Android.",
                "Sebagai pengganti database lokal seperti SQLite."
            ),
            1  // Index jawaban benar (pilihan nomor 2)
        ),

        // =======================
        // 2. SOAL KEDUA
        // =======================
        Quiz(
            "Mengapa Kotlin dianggap lebih aman dibandingkan Java dalam pemrograman Android?",
            listOf(
                "Karena Kotlin tidak membutuhkan deklarasi variabel.",
                "Karena Kotlin tidak bisa crash meskipun ada error dalam kode.",
                "Karena Kotlin memiliki Null Safety yang mencegah NullPointerException saat runtime.",
                "Karena Kotlin tidak menggunakan konsep class dan objek.",
                "Karena semua code Kotlin otomatis diuji sebelum dijalankan."
            ),
            2
        ),

        // =======================
        // 3. SOAL KETIGA
        // =======================
        Quiz(
            "Dalam Android Studio, apa fungsi utama dari sebuah Activity?",
            listOf(
                "Sebagai file konfigurasi untuk menentukan warna dan tema aplikasi.",
                "Sebagai komponen yang bertanggung jawab menampilkan UI dan menangani interaksi pengguna.",
                "Sebagai folder utama untuk menyimpan semua gambar dan aset aplikasi.",
                "Sebagai modul keamanan yang mengatur izin aplikasi (permission).",
                "Sebagai tempat menyimpan seluruh database aplikasi."
            ),
            1
        ),

        // Semua soal berikutnya sama formatnya (tidak diulang komentarnya)
        Quiz(
            "Manakah pernyataan yang benar mengenai XML layout pada aplikasi Android?",
            listOf(
                "XML layout hanya digunakan untuk mendesain splash screen.",
                "XML layout berfungsi sebagai tempat menuliskan logika aplikasi.",
                "XML layout digunakan untuk mendefinisikan struktur tampilan seperti button, text, dan layout.",
                "XML layout tidak bisa dikombinasikan dengan kode Kotlin.",
                "XML layout hanya digunakan pada aplikasi berbasis WebView."
            ),
            2
        ),

        Quiz(
            "Pada pemrograman Kotlin, apa kegunaan keyword *fun*?",
            listOf(
                "Untuk mendeklarasikan variabel baru dalam sebuah class.",
                "Untuk mendefinisikan fungsi yang dapat dipanggil kembali.",
                "Untuk membuat komentar dalam kode.",
                "Untuk melakukan import library dari package tertentu.",
                "Untuk mendefinisikan class data."
            ),
            1
        ),

        Quiz(
            "Pada Android, kapan metode onCreate() dipanggil?",
            listOf(
                "Saat aplikasi dihapus oleh sistem.",
                "Saat Activity dibuat pertama kali dan layout diinisialisasi.",
                "Saat pengguna menekan tombol Home.",
                "Saat Activity tampil penuh dan user mulai berinteraksi.",
                "Saat perangkat masuk mode sleep."
            ),
            1
        ),

        Quiz(
            "Apa fungsi utama Intent dalam Android?",
            listOf(
                "Untuk menyimpan data ke dalam file txt.",
                "Untuk berpindah antar Activity atau mengirim data.",
                "Untuk menghapus cache aplikasi.",
                "Untuk mengganti tema aplikasi.",
                "Untuk mengatur rotasi layar."
            ),
            1
        ),

        Quiz(
            "Apa fungsi RecyclerView dalam Android?",
            listOf(
                "Menampilkan daftar data dalam bentuk list yang dapat digulir.",
                "Sebagai tempat menyimpan data sementara.",
                "Untuk mengganti warna background aplikasi.",
                "Untuk mengatur navigasi antar fragment.",
                "Untuk menjalankan proses network."
            ),
            0
        ),

        Quiz(
            "Apa kegunaan ViewBinding di Android Studio?",
            listOf(
                "Untuk mengakses internet secara otomatis.",
                "Untuk mempermudah mengakses view tanpa findViewById.",
                "Untuk menghapus error saat compile.",
                "Untuk membuat database offline.",
                "Untuk memaksimalkan ukuran aplikasi."
            ),
            1
        ),

        Quiz(
            "Apa perbedaan utama antara LinearLayout dan ConstraintLayout?",
            listOf(
                "ConstraintLayout mendukung penataan posisi elemen yang fleksibel.",
                "LinearLayout hanya bisa digunakan pada Android 13 ke atas.",
                "ConstraintLayout tidak mendukung margin.",
                "LinearLayout dapat menampilkan animasi otomatis.",
                "ConstraintLayout tidak bisa menempatkan dua elemen berdampingan."
            ),
            0
        ),

        Quiz(
            "Bagaimana cara menampilkan Toast di Android?",
            listOf(
                "Toast.showText(\"Hello\")",
                "ToastMessage(\"Hello\").run",
                "Toast.makeText(context, \"Hello\", Toast.LENGTH_SHORT).show()",
                "Message.toast(context, \"Hello\")",
                "Show.Toast(context, \"Hello\")"
            ),
            2
        ),

        Quiz(
            "Apa arti dari dp pada Android?",
            listOf(
                "Densitas pixel layar.",
                "Satuan panjang yang menyesuaikan kepadatan layar.",
                "Jumlah pixel per baris.",
                "Unit untuk warna.",
                "Unit untuk ukuran font."
            ),
            1
        ),

        Quiz(
            "Apa fungsi manifest (AndroidManifest.xml)?",
            listOf(
                "Tempat menyimpan semua class yang ada di aplikasi.",
                "Untuk mendesain tampilan aplikasi.",
                "Untuk mendaftarkan Activity, permission, dan konfigurasi aplikasi.",
                "Untuk menyimpan layout XML.",
                "Untuk menampilkan gambar."
            ),
            2
        ),

        Quiz(
            "Fitur apa yang memungkinkan navigasi antar Fragment?",
            listOf(
                "Firebase Auth",
                "RecyclerView",
                "Navigation Component",
                "Picasso Library",
                "Room Database"
            ),
            2
        ),

        Quiz(
            "Apa kegunaan keyword val dalam Kotlin?",
            listOf(
                "Mendefinisikan variabel yang nilainya tidak dapat diubah.",
                "Membuat variabel global otomatis.",
                "Membuat animasi di Android.",
                "Menghapus variabel otomatis.",
                "Mendefinisikan objek layout."
            ),
            0
        )
    )

    // ==================================================
    // MENYIMPAN JAWABAN USER
    // ==================================================
    var userAnswers = ArrayList<Int>()
    // Variabel global untuk mencatat pilihan jawaban user.
    // Diisi setiap user menjawab quiz.
}
