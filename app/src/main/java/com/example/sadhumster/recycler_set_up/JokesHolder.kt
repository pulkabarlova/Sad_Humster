package com.example.sadhumster.recycler_set_up

import androidx.recyclerview.widget.RecyclerView
import com.example.sadhumster.model.Joke
import com.example.sadhumster.databinding.JokeItemBinding

class JokesHolder(private val binding: JokeItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(joke: Joke) {
        binding.header.text = "#" + "" + (position + 1).toString() + " " + joke.category
        binding.question.text = joke.question
    }
}