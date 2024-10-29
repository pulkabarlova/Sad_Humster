package com.example.sadhumster

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.media.MediaPlayer
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sadhumster.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = JokesAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recycleView.layoutManager = LinearLayoutManager(this)
        binding.recycleView.adapter = adapter
    }
}