package com.example.sadhumster

import androidx.recyclerview.widget.RecyclerView
import com.example.sadhumster.databinding.JokeItemBinding

class JokesHolder(private val binding: JokeItemBinding): RecyclerView.ViewHolder(binding.root) {
    val jokesArray = listOf("What has a face and two hands but no arms or legs?",
        "What does a house wear?", "What kind of murderer is full of fiber?",
        "How does a bee get to school?", "What kind of running leads to walking?",
        "I Start with M, end with X, and have a never-ending amount of letters. What am I?",
        "What is orange and sounds like a parrot?")
    val answerArray = listOf("A clock", "Ad-dress.", "A cereal killer",
        "On a buzz!", "Running out of gas!",
        "A mailbox", "A carrot.")

    fun bind(number: Int) {
        binding.imageView.setOnClickListener {
            binding.text2.text = answerArray[number]
        }
        val num = number + 1
        binding.header.text = "Joke #$num"
        binding.text1.text = jokesArray[number]
        binding.text2.text = ""
    }
}