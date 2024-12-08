package com.example.sadhumster.presentation.recycler

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.sadhumster.R
import com.example.sadhumster.domain.model.Joke
import com.example.sadhumster.databinding.JokeItemBinding
import com.example.sadhumster.presentation.fragments.MainFragment

class JokesHolder(private val binding: JokeItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(joke: Joke, context: MainFragment) {
        var from: String = ""
        when (joke.from) {
            "fromInternet" -> from = context.getString(R.string.from_internet)
            "fromFragment" -> from = context.getString(R.string.from_fragment)
        }
        val s = context.getString(R.string.joke_title, (position + 1), joke.category, from)
        binding.header.text = s
        binding.question.text = joke.setup
    }
}