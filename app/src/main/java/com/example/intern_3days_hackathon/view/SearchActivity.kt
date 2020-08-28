package com.example.intern_3days_hackathon.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.intern_3days_hackathon.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val navController = findNavController(this, R.id.nav_fragment)
        setupActionBarWithNavController(navController,
        AppBarConfiguration(
                setOf(R.id.searchFragment, R.id.savedEventFragment, R.id.userFragment, R.id.savedEventDetailFragment)
        ))

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation)
        bottomNavigationView.setupWithNavController(navController)

    }
}
