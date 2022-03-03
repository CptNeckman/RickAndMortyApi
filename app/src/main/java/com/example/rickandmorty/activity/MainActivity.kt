package com.example.rickandmorty.activity

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.rickandmorty.R
import com.example.rickandmorty.activity.FragmentCharacter.view.CharacterFragment
import com.example.rickandmorty.activity.FragmentEpisode.view.EpisodeFragment
import com.example.rickandmorty.activity.FragmentLocation.view.LocationFragment
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private val characterFragment = CharacterFragment()
    private val locationFragment = LocationFragment()
    private val episodeFragment = EpisodeFragment()
    lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this,R.color.backgroundGrey)))
        replaceFragment(characterFragment)
        bottomNavigation = findViewById(R.id.bottom_navigation)
        bottomNavigation.itemIconTintList = null
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_character -> replaceFragment(characterFragment)
                R.id.menu_location -> replaceFragment(locationFragment)
                R.id.menu_episode -> replaceFragment(episodeFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}
