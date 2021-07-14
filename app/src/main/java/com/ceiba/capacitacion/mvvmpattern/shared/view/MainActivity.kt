package com.ceiba.capacitacion.mvvmpattern.shared.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.ceiba.capacitacion.mvvmpattern.R
import com.ceiba.capacitacion.mvvmpattern.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private fun setUpBottomNavigation() {
        (supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment).let { navHostFragment ->
            navHostFragment.navController
            findViewById<BottomNavigationView>(R.id.bottom_navigation)
                    .setupWithNavController(navHostFragment.navController)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpBottomNavigation()
    }
}