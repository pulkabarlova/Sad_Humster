package com.example.sadhumster

import androidx.recyclerview.widget.RecyclerView
import com.example.sadhumster.databinding.JokeItemBinding

class JokesHolder(private val binding: JokeItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(joke: Joke) {
//        binding.imageView.setOnClickListener {
//            if (binding.answer.text == joke.answer)
//                binding.answer.text = ""
//            else {
//                binding.answer.text = joke.answer
//            }
//        }
        binding.header.text = joke.category
        binding.question.text = joke.question
    }
}