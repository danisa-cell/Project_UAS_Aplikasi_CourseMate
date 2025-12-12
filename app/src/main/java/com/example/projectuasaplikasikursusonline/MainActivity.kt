package com.example.projectuasaplikasikursusonline
// → Package utama tempat file MainActivity berada.

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.projectuasaplikasikursusonline.R
import com.google.android.material.bottomnavigation.BottomNavigationView
// → Import library untuk Navigation Component dan BottomNavigationView.


class MainActivity : AppCompatActivity() {
    // → Activity utama aplikasi, yang pertama kali dijalankan setelah splash/login.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // → Menghubungkan activity ke layout activity_main.xml


        // ============================================================
        //                     NAVIGATION HOST
        // ============================================================
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        // → Mengambil fragment container (NavHost) dari layout.
        //   NavHost adalah tempat semua fragment tampil bergantian.


        val navController = navHostFragment.navController
        // → Controller untuk mengatur perpindahan antar fragment
        //   berdasarkan navigation graph.


        // ============================================================
        //                BOTTOM NAVIGATION SETUP
        // ============================================================
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        // → Mengambil komponen BottomNavigationView dari layout.

        bottomNav.setupWithNavController(navController)
        // → Menghubungkan bottom navigation dengan navController.
        //   Setiap klik menu bottom nav akan otomatis berpindah fragment.


    }
}
