package com.example.sadhumster.presentation.recycler

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.sadhumster.R
import com.example.sadhumster.domain.model.Joke
import com.example.sadhumster.databinding.JokeItemBinding

class JokesHolder(private val binding: JokeItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(joke: Joke, context: Fragment) {
        var from: String = ""
        when (joke.from) {
            "fromInternet" -> from = context.getString(R.string.from_internet)
            "fromFragment" -> from = context.getString(R.string.from_fragment)
        }
        val s = context.getString(R.string.joke_title, (position + 1), joke.category, from)
        binding.header.text = s
        binding.question.text = joke.setup
        binding.addToSelected.isChecked = joke.favourite != 0
        binding.addToSelected.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
                joke.favourite = 1
            else
                joke.favourite = 0
        }
    }
}