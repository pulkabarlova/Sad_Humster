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
    private val adapter by lazy { JokesAdapter() }
    val jokesList = listOf(
        Joke("Joke #1", "What has a face and two hands but no arms or legs?", "A clock"),
        Joke("Joke #2", "What does a house wear?", "Ad-dress."),
        Joke("Joke #3", "What kind of murderer is full of fiber?", "A cereal killer"),
        Joke("Joke #4", "How does a bee get to school?", "On a buzz!"),
        Joke("Joke #5", "What kind of running leads to walking?", "Running out of gas!"),
        Joke("Joke #6", "I Start with M, end with X, and have a never-ending amount of letters. What am I?", "A mailbox"),
        Joke("Joke #7", "What is orange and sounds like a parrot?", "A carrot.")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecycler()
    }

    private fun setUpRecycler() {
        binding.recycleView.layoutManager = LinearLayoutManager(this)
        binding.recycleView.adapter = adapter
        adapter.setData(jokesList)
    }
}