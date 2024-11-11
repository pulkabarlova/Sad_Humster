package com.example.sadhumster.recycler_set_up

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sadhumster.model.Joke
import com.example.sadhumster.R
import com.example.sadhumster.databinding.JokeItemBinding
import com.example.sadhumster.fragments.FragmentJokesDetails
import com.example.sadhumster.fragments.MainFragment

class JokesAdapter(
    private val context: MainFragment,
    private val fragmentManager: FragmentManager
) : RecyclerView.Adapter<JokesHolder>() {

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
            val fragment = FragmentJokesDetails()
            fragment.arguments = Bundle().apply {
                putInt("jokeIndex", position)
            }
            fragmentManager.beginTransaction()
                .hide(fragmentManager.fragments[0])
                .addToBackStack(null)
                .add(R.id.container, fragment)
                .commit()
        }
    }
}