package com.example.projectuasaplikasikursusonline

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.projectuasaplikasikursusonline.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // NavHost
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // BottomNavigationView
        bottomNav = findViewById(R.id.bottom_navigation)
        bottomNav.setupWithNavController(navController)

        // Atur fragment tertentu agar bottomNav hilang
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {

                R.id.detailFragment,
                R.id.paymentFragment,
                R.id.paymentSuccessFragment -> {    // tambahkan ini
                    bottomNav.visibility = View.GONE
                }

                else -> {
                    bottomNav.visibility = View.VISIBLE
                }
            }
        }
    }

    // === Fungsi umum agar fragment bisa sembunyikan/munculkan bottom nav ===
    fun setBottomNavVisibility(isVisible: Boolean) {
        bottomNav.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}
