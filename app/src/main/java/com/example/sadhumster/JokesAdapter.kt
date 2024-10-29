package com.example.sadhumster

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sadhumster.databinding.JokeItemBinding

class JokesAdapter(private val context: Context) : RecyclerView.Adapter<JokesHolder>() {
    private var jokes = emptyList<Joke>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = JokeItemBinding.inflate(inflater)
        return JokesHolder(binding)
    }

    override fun getItemCount(): Int {
        return jokes.size

    }

    fun setData(newList: List<Joke>) {
        val diffCallback = JokesDiffUtil(jokes, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        jokes = newList
        diffResult.dispatchUpdatesTo(this)

    }

    override fun onBindViewHolder(holder: JokesHolder, position: Int) {
        holder.bind(jokes[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(context, JokeDetails::class.java)
            intent.putExtra("jokeIndex", position)
            context.startActivity(intent)
        }
    }
}