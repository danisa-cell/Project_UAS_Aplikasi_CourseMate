package com.example.projectuasaplikasikursusonline

data class Quiz(
    val question: String,
    val options: List<String>,
    val correctIndex: Int
)

object QuizData {

    val questions = listOf(
        Quiz(
            "Apa fungsi utama bahasa Kotlin dalam pengembangan aplikasi Android modern?",
            listOf(
                "Sebagai bahasa markup untuk mendesain tampilan aplikasi Android.",
                "Sebagai bahasa utama untuk menulis logika program dan mengelola fungsionalitas aplikasi.",
                "Sebagai tool otomatis yang menghasilkan file layout XML.",
                "Sebagai sistem penyimpanan berbasis cloud untuk aplikasi Android.",
                "Sebagai pengganti database lokal seperti SQLite."
            ),
            1
        ),

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
                "Untuk mendefinisikan fungsi yang berisi logika tertentu yang dapat dipanggil kembali.",
                "Untuk membuat komentar yang menjelaskan fungsi dalam kode.",
                "Untuk melakukan import library dari package tertentu.",
                "Untuk mendefinisikan class data dalam aplikasi Android."
            ),
            1
        ),

        Quiz(
            "Pada Android, kapan metode onCreate() dipanggil?",
            listOf(
                "Saat aplikasi sedang dihapus dari sistem.",
                "Saat Activity dibuat untuk pertama kali dan layout mulai diinisialisasi.",
                "Saat pengguna menekan tombol Home.",
                "Saat Activity sudah tampil sepenuhnya dan user mulai berinteraksi.",
                "Saat perangkat masuk ke mode sleep."
            ),
            1
        )
    )
}
