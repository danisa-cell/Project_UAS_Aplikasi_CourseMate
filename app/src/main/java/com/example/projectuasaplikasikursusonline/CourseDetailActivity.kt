package com.example.projectuasaplikasikursusonline
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.projectuasaplikasikursusonline.storage.CourseProgressStorage
import com.example.projectuasaplikasikursusonline.R

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

            Banyak fitur Android seperti Activity, View, dan Fragment dibangun dengan konsep OOP.
        """.trimIndent(),

        "Constructor & Init" to """
            Constructor adalah bagian dari class yang dijalankan saat object dibuat. Kotlin menyediakan primary dan secondary constructor.

            Blok init digunakan untuk menjalankan kode tambahan setelah object dibuat. 
            Ini penting untuk inisialisasi nilai atau validasi awal.

            Penggunaan constructor yang tepat akan membantumu membuat object yang aman dan siap digunakan sejak awal.

            Kotlin juga mendukung default parameter, sehingga kamu bisa membuat constructor lebih fleksibel tanpa perlu membuat banyak versi.

            Kamu juga akan belajar bagaimana constructor bekerja dalam class turunan dan bagaimana cara memanggil constructor parent.
        """.trimIndent(),

        "Inheritance Kotlin" to """
            Inheritance memungkinkan sebuah class mewarisi property dan function dari class lain. 
            Kotlin menggunakan keyword open agar class bisa diwariskan.

            Dengan inheritance, kamu dapat menggunakan kembali kode dari class induk tanpa harus menulis ulang.
        Inheritance dalam Kotlin adalah mekanisme yang memungkinkan sebuah class mewarisi properti dan fungsi dari class lain. Dengan cara ini, sebuah class yang disebut child class atau subclass dapat menggunakan fitur yang sudah dimiliki oleh parent class tanpa perlu menulis ulang seluruh logika. Konsep ini sangat berguna untuk membuat struktur kode yang lebih rapi, terorganisir, dan mudah dikembangkan.

        Secara default, class di Kotlin tidak bisa diwariskan. Untuk membuat sebuah class dapat menjadi parent class, Kotlin menggunakan keyword open. Artinya, jika kamu ingin sebuah class bisa menjadi dasar bagi class lainnya, kamu harus menandainya dengan open. Tanpa keyword tersebut, Kotlin akan menganggap class itu final dan tidak bisa diturunkan. Hal ini membuat Kotlin lebih aman karena mencegah pewarisan yang tidak sengaja.

        Dengan inheritance, kamu dapat memanfaatkan kembali kode yang sudah ada. Misalnya, jika kamu memiliki class Hewan dengan properti nama dan fungsi bergerak(), maka class Kucing dan Anjing dapat mewarisi fungsi dan properti tersebut. Kamu tidak perlu membuat fungsi yang sama berkali-kali, sehingga kode menjadi lebih efisien dan mudah dipelihara.

        Kotlin juga mendukung method overriding, artinya subclass dapat menulis ulang fungsi yang diwarisi dengan versi yang berbeda. Untuk mengizinkan fungsi di parent class bisa dioverride, kamu perlu menandainya dengan keyword open juga. Dengan mekanisme ini, kamu bisa membuat perilaku berbeda pada setiap subclass meskipun mereka berbagi struktur dasar yang sama.
        """.trimIndent(),

        "Abstract & Interface" to """
            Abstract class adalah class yang tidak dapat dibuat objeknya secara langsung.
            Interface digunakan untuk membuat kontrak bahwa class wajib memiliki fungsi tertentu.
       Abstract class adalah class yang tidak dapat dibuat objeknya secara langsung. Class ini biasanya digunakan sebagai kerangka dasar yang hanya mendefinisikan struktur umum untuk subclass. Abstract class dapat memiliki fungsi abstract (yang belum memiliki isi) dan juga fungsi biasa yang sudah memiliki implementasi. Hal ini membuat abstract class fleksibel dalam memberikan template dasar bagi class turunan.

       Interface di Kotlin digunakan untuk membuat kontrak yang harus dipenuhi oleh class yang mengimplementasikannya. Dalam interface, kamu dapat mendefinisikan fungsi tanpa implementasi maupun dengan implementasi default. Perbedaannya dengan abstract class adalah sebuah class bisa mengimplementasikan lebih dari satu interface, sehingga interface sangat cocok untuk kebutuhan multiple behavior.

       Abstract class cocok digunakan jika kamu ingin membuat hirarki class dengan shared code yang kuat. Misalnya, abstract class Kendaraan bisa memiliki fungsi hidupkanMesin() yang sudah memiliki implementasi, sementara fungsi seperti bergerak() dideklarasikan sebagai abstract sehingga setiap subclass wajib membuat versinya sendiri. Dengan begitu, semua subclass memiliki struktur yang sama namun perilaku bisa berbeda.

       Sementara itu, interface lebih cocok ketika kamu ingin mendefinisikan kemampuan tertentu yang dapat dimiliki oleh beberapa class yang tidak berhubungan. Misalnya, interface Terbang dapat diimplementasikan oleh class Burung, Pesawat, atau bahkan Roket. Dengan memahami kapan menggunakan abstract class dan kapan menggunakan interface, kode akan menjadi lebih modular dan mudah dikembangkan.
       
        """.trimIndent(),

        "Data Class" to """
            Data class digunakan untuk menyimpan data tanpa perlu menulis fungsi tambahan seperti toString(), equals(), atau hashCode(). 
            Kotlin secara otomatis membuatkan fungsi-fungsi tersebut untukmu.
       Data class adalah fitur Kotlin yang dirancang untuk menyimpan data tanpa perlu menulis banyak kode tambahan. Biasanya, saat membuat class untuk menyimpan data, kita perlu membuat fungsi-fungsi seperti toString(), equals(), hashCode(), dan copy(). Namun, dengan data class, Kotlin secara otomatis membuatkan fungsi-fungsi tersebut untuk mempermudah pengelolaan data.

       Untuk membuat data class, kamu cukup menuliskan keyword data di depan deklarasi class, dan minimal harus memiliki satu properti di dalam constructor. Contohnya: data class User(val nama: String, val umur: Int). Dengan deklarasi sederhana ini, kamu sudah bisa mendapatkan representasi string otomatis ketika data dicetak, serta kemudahan membandingkan dua objek.

       Fitur copy() pada data class membuatmu dapat membuat salinan objek dengan cepat. Misalnya, jika kamu ingin menggandakan objek User tetapi hanya mengubah umur, kamu bisa menulis user.copy(umur = 25). Ini sangat berguna dalam kasus seperti perubahan nilai pada state aplikasi atau mengelola data immutable.

       Data class sangat cocok digunakan dalam aplikasi Kotlin modern, terutama untuk kebutuhan seperti model data, response API, atau struktur informasi yang bersifat tetap. Dengan memanfaatkan data class, kode menjadi lebih bersih, ringkas, dan mudah dibaca tanpa perlu membuat fungsi boilerplate yang berulang.
       
        """.trimIndent(),

        "Collection List" to """
            List digunakan untuk menyimpan data dalam bentuk urutan. 
            Kotlin memiliki List (immutable) dan MutableList (mutable).
       List adalah salah satu struktur data paling dasar dalam Kotlin yang digunakan untuk menyimpan kumpulan nilai dalam urutan tertentu. Kamu bisa mengakses data berdasarkan indeks, mulai dari indeks 0. Kotlin menyediakan dua jenis list: List (immutable) dan MutableList (mutable), yang memiliki perbedaan penting dalam penggunaan sehari-hari.

       List (immutable) adalah list yang tidak bisa diubah setelah dibuat. Artinya, kamu tidak bisa menambahkan, menghapus, atau mengubah data di dalamnya. List ini cocok untuk data yang sifatnya tetap atau tidak boleh dimodifikasi, sehingga lebih aman dan mengurangi risiko bug. Kamu dapat membuatnya dengan listOf().

       Sementara itu, MutableList memungkinkanmu untuk mengubah isi list, seperti menambah item dengan add(), menghapus dengan remove(), atau mengubah nilai berdasarkan indeks. List ini cocok digunakan ketika data bersifat dinamis, misalnya daftar belanja, list pengguna aktif, atau data yang sering berubah.

       Kotlin memberikan banyak fungsi tambahan untuk memanipulasi list, seperti map, filter, sorted, dan lainnya. Fungsi-fungsi ini membuat pengolahan data menjadi lebih mudah dan expresif. Dengan memahami perbedaan antara List dan MutableList, kamu dapat memilih struktur data yang tepat dan menulis kode yang lebih aman serta efisien.
       
        """.trimIndent(),

        "Collection Map" to """
            Map adalah struktur data yang menyimpan pasangan key-value.
        Map adalah struktur data yang menyimpan data dalam bentuk pasangan key-value. Setiap key dalam Map harus unik, sedangkan value dapat berisi nilai apa saja. Map sangat cocok digunakan untuk menyimpan data yang membutuhkan akses cepat berdasarkan key, seperti dictionary, configuration, atau data terstruktur.

        Kotlin menyediakan dua jenis Map: Map (immutable) dan MutableMap (mutable). Pada Map immutable, kamu tidak dapat menambah atau menghapus pasangan key-value setelah Map dibuat. Sedangkan MutableMap memungkinkan perubahan data, seperti menambah entry dengan put() atau menghapusnya dengan remove().

        Keunggulan Map adalah kamu dapat mengakses nilai dengan cepat menggunakan key, misalnya: map["nama"]. Akses ini sangat efisien dan membuat kode lebih mudah dibaca. Map juga bisa sering digunakan untuk menyimpan data konfigurasi atau mapping tertentu, seperti daftar kode negara atau pasangan ID dan nama.

        Kotlin juga mendukung berbagai operasi lanjutan untuk Map, seperti iterasi melalui entries, mendapatkan semua key dengan keys, atau semua value dengan values. Kamu juga dapat memanfaatkan fungsi-fungsi seperti mapValues, filterKeys, dan lainnya untuk memproses data secara functional. Dengan Map, struktur data menjadi lebih rapi dan mudah dikelola.
        """.trimIndent(),

        "Exception Handling" to """
            Exception handling adalah cara untuk menangani error agar aplikasi tidak crash.
        Exception handling adalah mekanisme untuk menangani error agar aplikasi tidak berhenti atau crash secara tiba-tiba. Dalam Kotlin, error sering terjadi ketika data tidak valid, nilai null, atau operasi tertentu gagal. Untuk itu, Kotlin menyediakan cara untuk “menangkap” error tersebut dan memberikan solusi atau pesan yang lebih aman kepada pengguna.

        Kotlin menggunakan blok try, catch, dan finally untuk menangani exception. Blok try berisi kode yang berpotensi menyebabkan error, sedangkan catch menangani error tersebut jika terjadi. Dengan cara ini, aplikasi tetap berjalan meskipun ada masalah, karena error tidak dibiarkan menghentikan program.

        Blok finally dijalankan terlepas dari apakah error terjadi atau tidak. Bagian ini sering digunakan untuk membersihkan resource seperti menutup file, menghentikan koneksi, atau operasi lainnya yang harus dilakukan sampai selesai. Mechanism ini membuat kode lebih stabil dan aman digunakan.

        Selain itu, Kotlin juga mendukung membuat exception sendiri menggunakan keyword throw. Kamu bisa membuat error custom untuk situasi tertentu, misalnya ketika data login tidak valid. Dengan exception handling yang tepat, aplikasi menjadi lebih kuat dan memberikan pengalaman pengguna yang lebih baik.
        """.trimIndent(),


        "Make App Android" to """
            Materi ini mengajarkan cara membuat aplikasi Android dari nol menggunakan Kotlin.
        
        Materi ini mengajarkan cara membuat aplikasi Android dari nol menggunakan Kotlin sebagai bahasa utama. Kamu akan mempelajari konsep dasar seperti struktur project Android, cara kerja Activity, dan bagaimana UI ditampilkan melalui layout XML. Pemahaman awal ini sangat penting untuk memulai pengembangan aplikasi Android secara profesional.

        Selanjutnya, kamu akan belajar bagaimana menghubungkan kode Kotlin dengan tampilan menggunakan ViewBinding atau Jetpack Compose. Dengan memahami cara interaksi antara UI dan logika program, kamu dapat membuat tampilan yang dinamis dan interaktif. Di sini kamu juga belajar cara menangani event seperti klik tombol atau input pengguna.

        Materi juga mencakup penggunaan komponen penting Android seperti Intent, RecyclerView, ViewModel, LiveData, dan lainnya. Komponen-komponen ini diperlukan untuk membuat aplikasi yang modern, modular, dan mudah dikembangkan. Kamu juga akan mempelajari cara mengelola data, baik dari API maupun database lokal seperti Room.

        Pada akhirnya, kamu akan mempelajari cara melakukan build, testing, dan deployment aplikasi ke perangkat nyata atau Play Store. Dengan menguasai seluruh materi ini, kamu memiliki bekal yang cukup untuk membuat aplikasi Android profesional mulai dari konsep hingga publikasi.
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

        // ✅ PERBAIKAN: Set materi progress ke 100% (sudah baca)
        CourseProgressStorage.updateMaterialProgress(this, title, )

        btnBack.setOnClickListener { finish() }

        // ✅ PERBAIKAN: Langsung ke quiz tanpa update progress dulu
        btnQuiz.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra("courseTitle", title)
            intent.putExtra("courseId", title)  // ✅ Langsung pakai title

          startActivity(intent)
        }
    }
}
