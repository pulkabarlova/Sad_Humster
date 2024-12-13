package com.example.sadhumster.presentation.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.findFragment
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import com.example.sadhumster.databinding.ActivityMainBinding
import com.example.sadhumster.presentation.fragments.FragmentJokeAdd
import com.example.sadhumster.presentation.fragments.MainFragment
import com.example.sadhumster.domain.model.Joke
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            openFragment()
        }
    }

    private fun openFragment() {
        val fragment = MainFragment()
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .add(binding.container.id, fragment, "main")
            .commit()
    }
}