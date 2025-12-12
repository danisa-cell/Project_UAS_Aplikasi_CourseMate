package com.example.projectuasaplikasikursusonline
// → Package utama untuk file ini, sebagai bagian dari project Android.

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.projectuasaplikasikursusonline.R
import com.google.android.material.bottomnavigation.BottomNavigationView
// → Import library yang dibutuhkan seperti Navigation, BottomNav, dll.


class HomeActivity : AppCompatActivity() {
    // → Activity utama setelah login, yang menampilkan bottom navigation.

    private lateinit var bottomNav: BottomNavigationView
    // → Variable untuk menampung komponen BottomNavigationView.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // → Menghubungkan activity dengan layout activity_main.xml

        // ======================================================
        //                SETUP NAVIGATION HOST
        // ======================================================
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        // → Ambil NavHostFragment dari layout.
        //   Ini container untuk seluruh fragment pada bottom nav.

        val navController = navHostFragment.navController
        // → Controller untuk mengatur perpindahan fragment.


        // ======================================================
        //            SETUP BOTTOM NAVIGATION VIEW
        // ======================================================
        bottomNav = findViewById(R.id.bottom_navigation)
        // → Ambil ID BottomNavigationView dari layout.

        bottomNav.setupWithNavController(navController)
        // → Menghubungkan menu bottom navigation dengan navigation graph.


        // ======================================================
        //      SEMBUNYIKAN BOTTOM NAV DI HALAMAN TERTENTU
        // ======================================================
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {

                // → Jika masuk ke fragment-detail atau pembayaran,
                //   bottom nav akan disembunyikan (hilang).
                R.id.detailFragment,
                R.id.paymentFragment,
                R.id.paymentSuccessFragment -> {
                    bottomNav.visibility = View.GONE
                }

                // → Fragment lain: tampilkan bottom nav.
                else -> {
                    bottomNav.visibility = View.VISIBLE
                }
            }
        }
    }

    // ======================================================
    //      FUNGSI BISA DIPANGGIL DARI FRAGMENT LAIN
    // ======================================================
    fun setBottomNavVisibility(isVisible: Boolean) {
        bottomNav.visibility = if (isVisible) View.VISIBLE else View.GONE
        // → TRUE = tampilkan bottom nav, FALSE = sembunyikan.
        //   Berguna jika fragment ingin kontrol manual.
    }
}
