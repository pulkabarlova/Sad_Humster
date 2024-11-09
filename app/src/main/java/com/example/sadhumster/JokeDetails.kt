package com.example.sadhumster

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sadhumster.databinding.ActivityJokeDetailsBinding
import com.example.sadhumster.databinding.JokeItemBinding

class JokeDetails : AppCompatActivity() {
    private val jokesData = JokesData()
    private lateinit var binding: ActivityJokeDetailsBinding
    private var jokesList = jokesData.returnList()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityJokeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)
        binding.hamster.setImageResource(R.drawable.hamster)
        binding.header.text = jokesList[intent.getIntExtra("jokeIndex", 0)].category
        binding.question.text = jokesList[intent.getIntExtra("jokeIndex", 0)].question
        binding.answer.text = jokesList[intent.getIntExtra("jokeIndex", 0)].answer
    }
}
