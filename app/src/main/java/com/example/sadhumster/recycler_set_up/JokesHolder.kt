package com.example.sadhumster.recycler_set_up

import androidx.recyclerview.widget.RecyclerView
import com.example.sadhumster.R
import com.example.sadhumster.model.Joke
import com.example.sadhumster.databinding.JokeItemBinding
import com.example.sadhumster.fragments.MainFragment

class JokesHolder(private val binding: JokeItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(joke: Joke, context: MainFragment) {
        val s = context.getString(R.string.joke_title, (position + 1), joke.category)
        binding.header.text = s
        binding.question.text = joke.question
    }
}