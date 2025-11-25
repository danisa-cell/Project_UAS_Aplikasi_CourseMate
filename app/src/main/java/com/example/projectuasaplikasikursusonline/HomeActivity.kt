package com.example.projectuasaplikasikursusonline

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Ambil data login
        val username = intent.getStringExtra("USERNAME")
        val password = intent.getStringExtra("PASSWORD")

        val bundle = Bundle()
        bundle.putString("USERNAME", username)
        bundle.putString("PASSWORD", password)

        // ============================
        //  FIX BAGIAN NAV CONTROLLER
        // ============================
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController = navHostFragment.navController

        // Set data ke graph (WAJIB pakai getNavInflater!)
        val navGraph = navController.navInflater.inflate(R.navigation.home_graph)
        navGraph.setStartDestination(R.id.menu_home) // atau fragment awalmu
        navController.setGraph(navGraph, bundle)

        // Bottom Nav
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNav.setupWithNavController(navController)
    }
}
