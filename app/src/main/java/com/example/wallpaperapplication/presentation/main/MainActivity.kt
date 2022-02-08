package com.example.wallpaperapplication.presentation.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.wallpaperapplication.MyApplication
import com.example.wallpaperapplication.R
import com.example.wallpaperapplication.databinding.ActivityMainBinding
import com.example.wallpaperapplication.presentation.fragment.MainFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.content_menu.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var mainComponent: MainComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        mainComponent = (application as MyApplication).appComponent.mainComponent().create()
        mainComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(toolbar)
        init()
    }

    private fun init() {
        setupClickListener()
        setToolbarTitle("Backgrounds")
        startFragment(MainFragment())
    }

    companion object {
        var category: String = "Backgrounds"
    }

    private fun setupClickListener() {
        val toggle = ActionBarDrawerToggle(this, binding.drawerLayout, toolbar, R.string.open, R.string.close)
        toggle.isDrawerIndicatorEnabled = true
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        binding.navMenu.setNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        category = item.toString()
        setToolbarTitle(item.toString())
        startFragment(MainFragment())
        return true
    }

    private fun startFragment(fragment: Fragment) {
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        val frag = supportFragmentManager.beginTransaction()
        frag.replace(R.id.fragment_container, fragment).commit()
    }

    private fun setToolbarTitle(title: String) {
        supportActionBar?.title = title
    }
}