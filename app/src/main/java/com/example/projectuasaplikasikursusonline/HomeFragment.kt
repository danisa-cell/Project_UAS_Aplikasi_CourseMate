package com.example.projectuasaplikasikursusonline

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class HomeFragment : Fragment() {

    private lateinit var edtSearch: EditText
    private lateinit var rvCourse: RecyclerView
    private lateinit var adapter: CourseAdapter

    // ✅ GUNAKAN DRAWABLE YANG BENAR-BENAR ADA
    private val courseList = listOf(
        Course(
            "Android Development",
            "Rp 250.000",
            R.drawable.ic_android,
            "Budi Santoso",
            R.drawable.tutor_android,  // Gunakan gambar yang ada sebagai placeholder tutor
            "Kelas ini membahas cara membuat aplikasi Android dari dasar hingga mahir. Kamu akan belajar layout, activity, fragment, dan data transfer antar halaman. Selain itu, materi juga mencakup penggunaan RecyclerView, API, dan Firebase. Cocok untuk pemula yang ingin menjadi Android Developer profesional."
        ),
        Course(
            "UI/UX Design",
            "Rp 300.000",
            R.drawable.ic_figma,
            "Aulia Rahma",
            R.drawable.tutor_figma,
            "Di kelas ini kamu belajar membuat desain aplikasi yang menarik dan mudah digunakan. Materi mencakup design thinking, wireframe, UI component, hingga prototyping. Kamu juga akan mempelajari standar desain modern seperti material design. Sangat cocok untuk pemula yang ingin bekerja di bidang desain digital."
        ),
        Course(
            "Web Programming",
            "Rp 275.000",
            R.drawable.ic_web,
            "Rian Pratama",
            R.drawable.tutor_web,
            "Kelas ini mengajarkan dasar pemrograman web menggunakan HTML, CSS, dan JavaScript. Kamu juga diperkenalkan dengan konsep responsif dan interaktivitas website. Selain itu, terdapat materi backend dasar untuk memahami alur data. Cocok bagi kamu yang ingin menjadi web developer modern."
        ),
        Course(
            "Database MySQL",
            "Rp 200.000",
            R.drawable.ic_database,
            "Dewi Kurnia",
            R.drawable.tutor_data,
            "Kelas ini membahas penggunaan MySQL untuk mengelola dan mengolah data. Kamu akan belajar membuat tabel, relasi, dan query dasar hingga lanjutan. Materi juga mencakup optimasi database dan manajemen pengguna. Sangat sesuai untuk pemula yang ingin bekerja dengan data."
        ),
        Course(
            "Java Programming",
            "Rp 260.000",
            R.drawable.ic_java,
            "Fajar Nugroho",
            R.drawable.tutor_java,
            "Kelas Java ini cocok untuk pemula yang ingin memahami konsep OOP secara mendalam. Kamu akan belajar variabel, class, inheritance, dan polymorphism. Selain itu, materi mencakup exception handling dan file management. Kelas ini menjadi dasar kuat untuk Android atau backend."
        ),
        Course(
            "Cyber Security",
            "Rp 240.000",
            R.drawable.ic_cyber,
            "Arif Gunawan",
            R.drawable.tutor_cyber,
            "Kelas ini membahas dasar-dasar keamanan digital untuk melindungi sistem dan jaringan. Kamu akan belajar jenis serangan umum dan cara pencegahannya. Selain itu, terdapat praktik scanning dan analisis vulnerability. Cocok bagi yang ingin memulai karir di cyber security."
        ),
        Course(
            "Cloud Computing",
            "Rp 350.000",
            R.drawable.ic_cloud,
            "Rendra Mahesa",
            R.drawable.tutor_cloud,
            "Kelas ini mengenalkan layanan cloud seperti AWS, Azure, dan Google Cloud. Kamu belajar cara deploy aplikasi di cloud dan mengelola server virtual. Selain itu, materi mencakup konsep scaling, storage, dan security. Sangat bermanfaat untuk developer atau DevOps."
        ),
        Course(
            "Cyber Security Advanced",
            "Rp 400.000",
            R.drawable.ic_security,
            "Siti Lestari",
            R.drawable.tutor_security,
            "Kelas ini fokus pada teknik keamanan tingkat lanjut. Kamu akan belajar penetration testing, ethical hacking, serta analisis log. Materi juga mencakup manajemen serangan dan penggunaan tool profesional. Cocok untuk kamu yang sudah memahami dasar cyber security."
        ),
        Course(
            "Machine Learning",
            "Rp 420.000",
            R.drawable.ic_machinelearning,
            "Fikri Ananda",
            R.drawable.tutor_ml,
            "Kelas ini membahas konsep machine learning dari dasar hingga model prediksi. Kamu akan belajar supervised, unsupervised, dan evaluasi model. Selain itu, kamu juga mempraktikkan penggunaan Python dan library ML. Kelas ini cocok bagi yang ingin masuk dunia AI."
        ),
        Course(
            "Data Analytics",
            "Rp 390.000",
            R.drawable.ic_data,
            "Rina Maharani",
            R.drawable.tutor_data,
            "Kelas ini mengajarkan cara mengolah data untuk menghasilkan insight. Kamu akan belajar Excel, SQL, dan visualisasi data menggunakan tools modern. Materi juga mencakup storytelling dengan data. Cocok untuk memulai karir sebagai data analyst."
        ),
        Course(
            "React Native",
            "Rp 280.000",
            R.drawable.ic_reactnative,
            "Yoga Firmansyah",
            R.drawable.tutor_reactnative,
            "Kelas ini membahas pembuatan aplikasi mobile lintas platform menggunakan React Native. Kamu belajar komponen, navigasi, dan API fetch. Materi juga mencakup state management dan deployment. Cocok bagi yang ingin membuat aplikasi iOS dan Android sekaligus."
        ),
        Course(
            "Fullstack Web Developer",
            "Rp 500.000",
            R.drawable.ic_fullstack,
            "Zaki Nurhadi",
            R.drawable.tutor_fullstack,
            "Kelas ini membahas frontend dan backend secara lengkap. Kamu belajar HTML, CSS, JS, lalu lanjut Node.js atau PHP. Materi juga mencakup database, API, dan deployment. Cocok untuk yang ingin menjadi fullstack developer."
        ),
        Course(
            "Flutter Development",
            "Rp 320.000",
            R.drawable.ic_flutter,
            "Dani Saputra",
            R.drawable.tutor_flutter,
            "Kelas ini mengajarkan cara membuat aplikasi mobile menggunakan Flutter. Kamu belajar widget, state management, dan integrasi API. Selain itu, materi mencakup animasi dan deployment. Cocok bagi pemula maupun developer yang ingin mencoba teknologi baru."
        ),
        Course(
            "Artificial Intelligence",
            "Rp 450.000",
            R.drawable.ic_ai,
            "Syifa Lestari",
            R.drawable.tutor_ai,
            "Kelas AI ini membahas konsep kecerdasan buatan dan implementasinya. Kamu belajar neural network, deep learning, dan natural language processing. Materi juga mencakup penggunaan framework modern. Cocok untuk kamu yang ingin memahami teknologi AI lebih dalam."
        ),
        Course(
            "DevOps Engineer",
            "Rp 480.000",
            R.drawable.ic_devops,
            "Ridho Pratama",
            R.drawable.tutor_devops,
            "Kelas ini mengenalkan praktik DevOps untuk mempercepat proses pengembangan aplikasi. Kamu belajar CI/CD, Docker, dan pipeline otomasi. Selain itu, materi mencakup monitoring dan cloud deployment. Cocok untuk developer maupun sysadmin."
        ),
        Course(
            "Digital Marketing",
            "Rp 230.000",
            R.drawable.ic_marketing,
            "Citra Anjani",
            R.drawable.tutor_marketing,
            "Kelas ini mengajarkan strategi pemasaran digital untuk bisnis modern. Kamu belajar SEO, iklan, dan social media campaign. Selain itu, materi mencakup analisis performa iklan. Sangat cocok untuk UMKM maupun calon marketer."
        ),
        Course(
            "Python Dasar",
            "Rp 220.000",
            R.drawable.ic_python,
            "Bayu Hidayat",
            R.drawable.tutor_python,
            "Kelas Python ini membahas dasar-dasar pemrograman dengan sintaks yang mudah dipahami. Kamu belajar variabel, list, loop, dan fungsi. Selain itu, materi mencakup mini project untuk melatih logika. Cocok untuk pemula yang ingin memulai programming."
        ),
        Course(
            "UI Animation with Figma",
            "Rp 310.000",
            R.drawable.ic_figma,
            "Nadia Fitria",
            R.drawable.tutor_figma,
            "Kelas ini membahas cara membuat animasi UI profesional di Figma. Kamu belajar prototype, micro-interaction, dan animasi halaman. Materi juga mencakup tips desain modern. Cocok bagi desainer yang ingin meningkatkan kualitas portofolio."
        ),
        Course(
            "Networking Fundamental",
            "Rp 270.000",
            R.drawable.ic_network,
            "Dimas Putra",
            R.drawable.tutor_network,
            "Kelas ini mengenalkan dasar jaringan komputer dan cara kerjanya. Kamu belajar IP, subnetting, router, dan troubleshooting. Selain itu, materi mencakup praktik konfigurasi perangkat jaringan. Cocok untuk calon network engineer."
        ),
        Course(
            "Game Development Unity",
            "Rp 360.000",
            R.drawable.ic_unity,
            "Andre Wijaya",
            R.drawable.tutor_unity,
            "Kelas ini mengajarkan dasar pembuatan game menggunakan Unity. Kamu belajar scene, object, script C#, dan animasi. Selain itu, materi mencakup pembuatan gameplay dan UI. Cocok untuk pemula yang ingin membuat game sendiri."
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        edtSearch = view.findViewById(R.id.edtSearch)
        rvCourse = view.findViewById(R.id.rvCourse)

        rvCourse.layoutManager = LinearLayoutManager(requireContext())

        adapter = CourseAdapter(courseList) { selectedCourse ->
            // ✅ KIRIM DATA DENGAN BUNDLE (Tanpa Safe Args)
            val bundle = Bundle().apply {
                putString("title", selectedCourse.title)
                putString("price", selectedCourse.price)
                putInt("imageRes", selectedCourse.imageRes)
                putString("tutorName", selectedCourse.tutorName)
                putInt("tutorImage", selectedCourse.tutorImage)
                putString("description", selectedCourse.description)
            }

            findNavController().navigate(
                R.id.action_homeFragment_to_detailFragment,
                bundle
            )
        }

        rvCourse.adapter = adapter

        // ✅ Fitur search
        edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val filtered = courseList.filter {
                    it.title.contains(s.toString(), ignoreCase = true)
                }
                adapter.filterList(filtered)
            }
        })

        return view
    }
}