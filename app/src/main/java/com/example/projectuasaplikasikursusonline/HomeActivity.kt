package com.example.projectuasaplikasikursusonline

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ambil navHostFragment dari XML
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController = navHostFragment.navController

        // PENTING: ID HARUS SAMA DENGAN activity_main.xml
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Hubungkan BottomNav dengan NavController
        bottomNav.setupWithNavController(navController)
    }
}
