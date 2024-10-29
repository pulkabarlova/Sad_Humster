package com.example.sadhumster

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sadhumster.databinding.JokeItemBinding

class JokesAdapter: RecyclerView.Adapter<JokesHolder>() {
    private val jokes = listOf(0,1,2,3,4,5,6)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = JokeItemBinding.inflate(inflater)
        return JokesHolder(binding)
    }

    override fun getItemCount(): Int {
        return jokes.size

    }

    override fun onBindViewHolder(holder: JokesHolder, position: Int) {
        holder.bind(jokes[position])
    }

}