package com.example.sadhumster

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sadhumster.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(savedInstanceState == null){
            openFragment()
        }
    }
    private fun openFragment(){
        val fragment = MainFragment()
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .add(binding.container.id, fragment)
            .commit()
    }
}