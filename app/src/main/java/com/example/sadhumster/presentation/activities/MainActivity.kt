package com.example.sadhumster.presentation.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.findFragment
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import com.example.sadhumster.R
import com.example.sadhumster.databinding.ActivityMainBinding
import com.example.sadhumster.presentation.fragments.FragmentJokeAdd
import com.example.sadhumster.presentation.fragments.MainFragment
import com.example.sadhumster.domain.model.Joke
import com.example.sadhumster.presentation.fragments.FragmentJokeSelected
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        openMainFragment()
        binding.navigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> openMainFragment()
                R.id.favourite -> openJokeSelectedFragment()
                else -> {
                    openMainFragment()
                }
            }
        }
    }

    private fun openMainFragment(): Boolean {
        val fragment = MainFragment()
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(binding.container.id, fragment, "main")
            .commit()
        return true
    }

    private fun openJokeSelectedFragment(): Boolean {
        val fragment = FragmentJokeSelected()
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(binding.container.id, fragment, "selected")
            .commit()
        return true
    }
}