package com.example.sadhumster.presentation.reycler_selected

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.sadhumster.R
import com.example.sadhumster.databinding.JokeItemBinding
import com.example.sadhumster.domain.model.Joke
import com.example.sadhumster.domain.model.JokeFromInternet
import com.example.sadhumster.domain.repository.JokeRepository
import com.example.sadhumster.presentation.fragments.FROM_FRAGMENT
import com.example.sadhumster.presentation.fragments.FROM_INTERNET
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SelectedHolder (private val binding: JokeItemBinding, private val repository: JokeRepository) : RecyclerView.ViewHolder(binding.root) {

    fun bind(joke: Joke, context: Fragment) {
        var from: String = ""
        when (joke.from) {
            FROM_INTERNET -> from = context.getString(R.string.from_internet)
            FROM_FRAGMENT -> from = context.getString(R.string.from_fragment)
        }
        val s = context.getString(R.string.joke_title, (position + 1), joke.category, from)
        binding.header.text = s
        binding.question.text = joke.setup
        binding.addToSelected.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked && joke.from == FROM_FRAGMENT)
                CoroutineScope(Dispatchers.IO).launch{
                    joke.favourite = 1
                    repository.updateJoke(joke)
                }
            else if (isChecked && joke.from == FROM_INTERNET) {
                val newJoke = JokeFromInternet(
                    category = joke.category,
                    setup = joke.setup,
                    delivery = joke.delivery,
                    from = FROM_INTERNET,
                    favourite = 1,
                    cachedAt = 0)
                CoroutineScope(Dispatchers.IO).launch {
                    repository.updateCachedJoke(newJoke)
                }
            }

        }
    }
}
