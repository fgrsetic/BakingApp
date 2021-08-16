package com.franjo.android.bakingapp.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.franjo.android.bakingapp.R
import com.franjo.android.bakingapp.databinding.ActivityBaseBinding
import dagger.hilt.android.AndroidEntryPoint

// This is a single activity application that uses the Navigation library
// Content is displayed by Fragments
@AndroidEntryPoint
class BaseActivity : AppCompatActivity() {

    private var appBarConfiguration: AppBarConfiguration? = null
    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_base)

        setSupportActionBar(binding.myToolbar)
        binding.myToolbar.title = ""

        val supportFragmentManager = supportFragmentManager
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        val navController: NavController
        if (navHostFragment != null) {
            navController = navHostFragment.navController
            appBarConfiguration = AppBarConfiguration.Builder(navController.graph).build()
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration!!)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        return (NavigationUI.navigateUp(navController, appBarConfiguration!!)
                || super.onSupportNavigateUp())
    }
}