package com.example.projectuasaplikasikursusonline

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CourseDetailActivity : AppCompatActivity() {

    private val courseDescriptions = mapOf(

        "Pengenalan Kotlin" to """
            Kotlin merupakan bahasa pemrograman modern yang dirancang untuk memberikan pengalaman coding yang lebih aman, bersih, ringkas, dan efisien. 
            Bahasa ini dikembangkan oleh JetBrains dan diumumkan secara resmi sebagai bahasa utama untuk Android oleh Google. 
            Dengan pendekatan yang lebih intuitif dibandingkan Java, Kotlin menjadi pilihan favorit bagi banyak developer pemula hingga profesional.

            Dalam dunia pengembangan aplikasi, Kotlin memberikan banyak fitur canggih seperti null-safety, extension function, lambda expression, 
            hingga interoperabilitas penuh dengan Java. Hal ini membuat Kotlin sangat fleksibel dan mudah diintegrasikan dengan kode lama.

            Selain lebih aman dari error, Kotlin juga memiliki struktur penulisan yang lebih pendek sehingga mempermudah dalam pembuatan aplikasi besar. 
            Developer dapat mengurangi jumlah kode berlebihan dan fokus pada logika inti program yang sedang dibuat.

            Kotlin juga mendukung paradigma pemrograman fungsional dan object-oriented. Kombinasi ini membantu developer menulis kode yang mudah dibaca 
            dan mudah dikelola dalam jangka panjang. Materi pengenalan ini akan memberimu gambaran lengkap tentang dasar Kotlin dan bagaimana cara memulainya.

            Dalam materi ini kamu akan belajar tentang cara kerja variabel, struktur dasar, serta langkah awal pembuatan aplikasi Android menggunakan Kotlin. 
            Pengetahuan ini penting untuk melanjutkan ke materi berikutnya yang lebih kompleks.

            Dengan memahami dasar Kotlin dengan baik, kamu akan memiliki landasan kuat untuk membangun aplikasi Android modern yang cepat, aman, dan profesional.
        """.trimIndent(),

        "Tipe Data & Variabel" to """
            Tipe data merupakan fondasi dari setiap program. Dalam Kotlin, tipe data terbagi menjadi beberapa kategori seperti angka (Int, Float, Double), 
            teks (String), karakter (Char), dan Boolean. Setiap tipe data memiliki kegunaan spesifik dan harus digunakan sesuai kebutuhan logika program.

            Variabel dalam Kotlin dapat dibuat menggunakan var untuk nilai yang dapat berubah dan val untuk nilai yang tetap. 
            Penggunaan val sangat dianjurkan untuk menciptakan kode yang lebih aman dan mengurangi kesalahan.

            Kotlin juga memiliki fitur type inference, sehingga kamu tidak perlu selalu menulis tipe datanya secara eksplisit. 
            Misalnya, cukup menulis val angka = 10, Kotlin otomatis mengenalinya sebagai Int.

            Pemahaman tentang tipe data akan sangat membantu ketika kamu membuat logika, proses perhitungan, validasi input pengguna, hingga penyimpanan data.

            Selain itu, Kotlin memastikan bahwa setiap variabel memiliki tipe yang jelas untuk menghindari error runtime yang sulit dilacak. 
            Ini adalah salah satu alasan mengapa Kotlin dianggap lebih aman dibandingkan Java.

            Pada materi ini kamu juga akan mempelajari bagaimana memanfaatkan tipe data mutable dan immutable dalam berbagai situasi. 
            Ini akan sangat penting ketika kamu membangun aplikasi yang kompleks.

            Dengan menguasai konsep tipe data dan variabel, kamu akan lebih siap dalam memahami materi lanjutan seperti operator, percabangan, dan fungsi.
        """.trimIndent(),

        "Operator & Expression" to """
            Operator digunakan untuk melakukan operasi terhadap nilai atau variabel. Kotlin menyediakan berbagai jenis operator seperti aritmatika, 
            perbandingan, logika, penugasan, dan bitwise. Setiap operator memiliki peran penting dalam menyusun logika aplikasi.

            Expression adalah kombinasi variabel, nilai, dan operator yang menghasilkan output tertentu. Misalnya, a + b * 2 merupakan expression yang valid.

            Dalam pemrograman sehari-hari, operator sering digunakan dalam percabangan, looping, validasi input, perhitungan matematika, 
            hingga pengambilan keputusan dalam program.

            Kotlin juga mendukung operator overloading, di mana kamu bisa menentukan cara kerja operator untuk class buatan sendiri. 
            Fitur ini sangat bermanfaat dalam pembuatan sistem besar seperti game engine atau aplikasi komputasi.

            Memahami operator secara mendalam akan membantumu menulis logika program yang lebih efisien dan mudah dibaca. 
            Operator yang digunakan dengan benar akan meningkatkan kualitas kode secara signifikan.

            Selain itu, kamu juga akan belajar tentang precedence atau tingkat prioritas operator. 
            Ini penting agar kamu tidak salah memaknai urutan proses dalam expression.

            Materi ini memberikan landasan logika yang kuat sebelum masuk ke percabangan dan looping.
        """.trimIndent(),

        "Percabangan (If/Else)" to """
            Percabangan digunakan untuk menentukan alur program berdasarkan kondisi tertentu. 
            Dengan percabangan, aplikasi dapat merespons input pengguna dengan cara yang lebih cerdas dan fleksibel.

            Kotlin menyediakan beberapa bentuk percabangan seperti if-else, nested if, dan when. 
            Struktur when sangat kuat karena bisa menggantikan switch-case dengan lebih ringkas dan fleksibel.

            Percabangan sering digunakan dalam form validasi, navigasi halaman, pengamanan data, dan pengolahan input.

            Pemahaman percabangan akan membantumu membuat alur program yang dinamis dan mudah dikendalikan. 
            Dengan kondisi yang tepat, aplikasi dapat memberikan hasil yang berbeda tergantung situasi.

            Selain itu, Kotlin juga mendukung expression-based condition, artinya if dapat mengembalikan nilai langsung. 
            Ini membuat penulisan kode lebih efisien.

            Kamu juga akan belajar tentang penggunaan operator logika seperti && dan || untuk menggabungkan beberapa kondisi sekaligus.

            Materi ini sangat penting karena percabangan merupakan elemen utama dalam hampir semua aplikasi.
        """.trimIndent(),

        "Perulangan (Loop)" to """
            Loop digunakan untuk menjalankan kode secara berulang berdasarkan kondisi tertentu. 
            Kotlin memiliki beberapa tipe loop seperti for-loop, while-loop, dan do-while.

            Loop digunakan ketika kamu ingin mengolah data dalam jumlah besar seperti list, array, map, atau hasil API.

            Dalam pengembangan aplikasi Android, loop sering digunakan untuk membuat daftar item, melakukan perhitungan, atau mengulang eksekusi otomatis.

            Kotlin juga menyediakan range-based looping, misalnya for (i in 1..10) yang membuat penulisan loop lebih sederhana.

            Selain itu, kamu akan belajar tentang penggunaan break dan continue untuk mengontrol alur loop agar lebih fleksibel.

            Loop merupakan salah satu bagian penting dalam optimasi program agar lebih efisien dan cepat.

            Dengan penguasaan loop yang baik, kamu bisa membuat fitur seperti pagination, animasi, hingga simulasi logika aplikasi.
        """.trimIndent(),

        "Function & Scope" to """
            Function adalah blok kode yang dirancang untuk menyelesaikan tugas tertentu. 
            Dengan menggunakan function, kamu bisa mengurangi pengulangan dan membuat kode lebih rapi.

            Kotlin memungkinkan function didefinisikan dengan parameter dan nilai kembali (return). 
            Hal ini mempermudah pembuatan logika yang lebih modular dan mudah diuji.

            Scope menentukan area di mana variabel dapat diakses. Kotlin memiliki local scope, global scope, dan class scope. 
            Pemahaman scope yang baik akan mencegah error seperti variabel tidak dikenali atau konflik nama variabel.

            Kotlin juga mendukung lambda dan higher-order function, yang memungkinkan penulisan function lebih expressive dan fleksibel.

            Function adalah bagian yang sangat penting ketika membuat aplikasi besar karena memudahkan pengorganisasian kode.

            Dalam materi ini kamu akan mempelajari cara membuat function yang efisien, reusable, dan mudah dipahami.

            Dengan penguasaan function yang baik, kamu akan lebih siap menghadapi konsep OOP di materi selanjutnya.
        """.trimIndent(),

        "OOP: Class & Object" to """
            Object-Oriented Programming (OOP) adalah paradigma pemrograman yang memodelkan objek dunia nyata ke dalam program. 
            Dalam Kotlin, class digunakan sebagai blueprint dan object adalah instansinya.

            OOP memungkinkan developer membuat struktur program yang lebih mudah diatur, terutama untuk proyek besar.

            Kamu akan belajar tentang property, function di dalam class, serta cara membuat dan menggunakan object di Kotlin.

            Konsep OOP juga mendukung enkapsulasi, yaitu perlindungan data agar tidak bisa diakses sembarangan.

            Dengan pemahaman OOP yang kuat, kamu dapat membangun aplikasi yang scalable dan mudah diperluas.

            Banyak fitur Android seperti Activity, View, dan Fragment dibangun dengan konsep OOP, 
            sehingga materi ini sangat penting untuk dipahami.

            OOP akan membuka jalan ke materi berikutnya seperti constructor, inheritance, dan interface.
        """.trimIndent(),

        "Constructor & Init" to """
            Constructor adalah bagian dari class yang dijalankan saat object dibuat. Kotlin menyediakan primary dan secondary constructor.

            Blok init digunakan untuk menjalankan kode tambahan setelah object dibuat. 
            Ini penting untuk inisialisasi nilai atau validasi awal.

            Penggunaan constructor yang tepat akan membantumu membuat object yang aman dan siap digunakan sejak awal.

            Kotlin juga mendukung default parameter, sehingga kamu bisa membuat constructor lebih fleksibel tanpa perlu membuat banyak versi.

            Kamu juga akan belajar bagaimana constructor bekerja dalam class turunan dan bagaimana cara memanggil constructor parent.

            Materi ini penting untuk mempersiapkan class yang kompleks dan data-driven.

            Constructor yang baik akan sangat membantu dalam pengembangan aplikasi yang besar dan dinamis.
        """.trimIndent(),

        "Inheritance Kotlin" to """
            Inheritance memungkinkan sebuah class mewarisi property dan function dari class lain. 
            Kotlin menggunakan keyword open agar class bisa diwariskan.

            Dengan inheritance, kamu dapat menggunakan kembali kode dari class induk tanpa harus menulis ulang.

            Materi ini sering digunakan dalam pembuatan komponen UI, model data, hingga logika aplikasi.

            Kamu juga akan belajar tentang overriding function, yaitu mengganti perilaku function di class induk.

            Inheritance membantu membuat program lebih terstruktur dan modular.

            Dalam proyek besar, inheritance mempermudah pembagian tanggung jawab antar class secara rapi.

            Memahami inheritance akan sangat membantumu dalam memahami sistem Android yang berbasis class hierarchy.
        """.trimIndent(),

        "Abstract & Interface" to """
            Abstract class adalah class yang tidak dapat dibuat objeknya secara langsung. Fungsinya adalah sebagai pola dasar bagi class lain.

            Interface digunakan untuk membuat kontrak bahwa class wajib memiliki fungsi tertentu. 
            Kotlin memungkinkan class mengimplementasikan banyak interface sekaligus.

            Ini sangat berguna dalam pembuatan sistem besar dengan struktur yang kompleks.

            Abstract dan interface membantu developer membuat desain aplikasi yang fleksibel dan mudah berkembang. 
            Kamu bisa membuat class dengan kemampuan tertentu tanpa terikat pada class induk tertentu.

            Materi ini juga sangat erat kaitannya dengan prinsip OOP seperti polymorphism.

            Dengan menguasai konsep ini, kamu dapat membangun aplikasi dengan arsitektur yang jauh lebih profesional.

            Android sendiri banyak menggunakan interface seperti OnClickListener dan Adapter interface.
        """.trimIndent(),

        "Data Class" to """
            Data class digunakan untuk menyimpan data tanpa perlu menulis fungsi tambahan seperti toString(), equals(), atau hashCode(). 
            Kotlin secara otomatis membuatkan fungsi-fungsi tersebut untukmu.

            Data class sangat penting ketika kamu bekerja dengan API, database, atau state UI.

            Kamu juga bisa menggunakan copy() untuk menduplikasi object dengan perubahan kecil pada nilai tertentu.

            Data class juga sangat cocok digunakan bersama dengan collection seperti List dan Map.

            Pemahaman data class akan memudahkanmu dalam mengelola data secara efisien.

            Banyak aplikasi modern seperti toko online, aplikasi booking, dan aplikasi berita menggunakan data class untuk memodelkan data.

            Dengan materi ini, kamu akan mampu membuat struktur data yang kuat dan aman.
        """.trimIndent(),

        "Collection List" to """
            List digunakan untuk menyimpan data dalam bentuk urutan. Kotlin memiliki List (immutable) dan MutableList (mutable).

            List sangat berguna ketika kamu ingin menampilkan data dalam jumlah banyak seperti daftar produk, daftar menu, atau list berita.

            Kotlin memberikan banyak function bawaan seperti filter, map, sortBy, reduce, dan lain-lain untuk mempermudah pengolahan data.

            Kamu juga akan belajar cara menambahkan item, menghapus item, dan mengakses data berdasarkan indeks tertentu.

            Collection List adalah fondasi penting dalam pembuatan aplikasi berbasis data.

            Pemahaman collection akan sangat membantumu dalam membuat fitur seperti RecyclerView dan pagination.

            Dengan menggunakan List secara efisien, aplikasi kamu akan berjalan lebih cepat dan responsif.
        """.trimIndent(),

        "Collection Map" to """
            Map adalah struktur data yang menyimpan pasangan key-value. 
            Key harus unik, sedangkan value boleh sama.

            Map sangat berguna untuk data yang membutuhkan pencarian cepat berdasarkan key tertentu, 
            seperti menyimpan konfigurasi, tabel referensi, atau data user.

            Kotlin menyediakan Map dan MutableMap yang memungkinkan pengelolaan data lebih fleksibel.

            Kamu juga akan belajar bagaimana menambah, menghapus, dan mengakses data menggunakan key.

            Map sering digunakan dalam aplikasi yang menangani data JSON, autentikasi, atau setting aplikasi.

            Pemahaman Map akan membuatmu lebih siap dalam mengolah data kompleks.

            Materi ini juga sangat penting untuk memahami struktur data pada backend dan API.
        """.trimIndent(),

        "Exception Handling" to """
            Exception handling adalah cara untuk menangani error agar aplikasi tidak crash. 
            Kotlin mendukung try, catch, finally, dan throw untuk menangani berbagai jenis error.

            Error bisa terjadi karena banyak hal seperti input pengguna yang tidak valid, koneksi internet terputus, atau file tidak ditemukan.

            Dengan menangani error dengan baik, aplikasi kamu akan jauh lebih stabil dan nyaman digunakan.

            Kamu juga akan belajar tentang custom exception untuk membuat jenis error sendiri.

            Penanganan error adalah salah satu indikator profesionalitas seorang developer.

            Materi ini membantu kamu mempersiapkan aplikasi untuk berbagai situasi tak terduga.

            Dengan pemahaman exception handling yang kuat, kamu bisa membuat aplikasi yang tidak mudah crash.
        """.trimIndent(),

        "Make app android" to """
            Materi ini mengajarkan kamu cara membuat aplikasi Android dari nol menggunakan Kotlin. 
            Kamu akan belajar tentang Activity, Intent, RecyclerView, Adapter, Layout XML, hingga struktur project Android.

            Pembuatan aplikasi Android membutuhkan pemahaman tentang UI, event handling, navigasi halaman, dan manajemen data.

            Kamu juga akan belajar bagaimana membangun halaman dengan gaya modern yang responsif.

            Selain itu, kamu akan memahami cara kerja komponen penting seperti Toast, Button, EditText, dan ImageView.

            Materi ini juga membahas praktik terbaik dalam membuat aplikasi yang cepat, aman, dan mudah dipelihara.

            Dengan menyelesaikan materi ini, kamu sudah mampu membuat aplikasi Android sederhana hingga menengah.

            Ini adalah langkah besar menuju kemampuan menjadi developer Android profesional.
        """.trimIndent()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_detail)

        val title = intent.getStringExtra("title") ?: "No Title"

        val tvTitle = findViewById<TextView>(R.id.tvCourseTitle)
        val tvDesc = findViewById<TextView>(R.id.tvCourseDescription)
        val btnQuiz = findViewById<Button>(R.id.btnQuiz)
        val btnBack = findViewById<ImageView>(R.id.btnBack)

        tvTitle.text = title

        tvDesc.text = courseDescriptions[title] ?: "Deskripsi belum tersedia."

        btnBack.setOnClickListener { finish() }

        btnQuiz.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra("title", title)
            startActivity(intent)
        }
    }
}
