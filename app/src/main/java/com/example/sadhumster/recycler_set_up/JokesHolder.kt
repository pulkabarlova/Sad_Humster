package com.example.sadhumster.recycler_set_up

import androidx.recyclerview.widget.RecyclerView
import com.example.sadhumster.model.Joke
import com.example.sadhumster.databinding.JokeItemBinding

class JokesHolder(private val binding: JokeItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(joke: Joke) {
        // setOnClickListener до реализации JоkesDetails, может пригодиться для следующей версии
        /*        binding.imageView.setOnClickListener {
                    if (binding.answer.text == joke.answer)
                        binding.answer.text = ""
                    else {
                        binding.answer.text = joke.answer
                    }
                }*/
        binding.header.text = joke.category
        binding.question.text = joke.question
    }
}