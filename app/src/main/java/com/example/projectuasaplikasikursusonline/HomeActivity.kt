package com.example.projectuasaplikasikursusonline

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ambil NavHostFragment
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController = navHostFragment.navController

        // ID harus sesuai dengan XML
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Sinkronkan BottomNav dengan Navigation Component
        bottomNav.setupWithNavController(navController)

        // 🔥 SEMBUNYIKAN BottomNav SAAT DI DetailFragment
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.detailFragment -> {
                    bottomNav.visibility = View.GONE   // Hilang saat buka detail
                }
                else -> {
                    bottomNav.visibility = View.VISIBLE // Muncul di fragment lain
                }
            }
        }
    }
}
