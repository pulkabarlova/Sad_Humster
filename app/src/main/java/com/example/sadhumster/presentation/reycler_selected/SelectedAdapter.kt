package com.example.sadhumster.presentation.reycler_selected

import android.os.Bundle
import android.view.LayoutInflater
import android.view.SearchEvent
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sadhumster.R
import com.example.sadhumster.databinding.JokeItemBinding
import com.example.sadhumster.domain.model.Joke
import com.example.sadhumster.domain.repository.JokeRepository
import com.example.sadhumster.presentation.fragments.FragmentJokeSelected
import com.example.sadhumster.presentation.fragments.FragmentJokesDetails

import com.example.sadhumster.presentation.recycler.JokesDiffUtil
import com.example.sadhumster.presentation.recycler.JokesHolder

class SelectedAdapter(
    private val context: FragmentJokeSelected,
    private val fragmentManager: FragmentManager,
    private val jokeRepository: JokeRepository
) : RecyclerView.Adapter<SelectedHolder>() {

    private var jokes = mutableListOf<Joke>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = JokeItemBinding.inflate(inflater)
        return SelectedHolder(binding, jokeRepository)
    }

    override fun getItemCount(): Int {
        return jokes.size
    }

    fun setData(newList: MutableList<Joke>) {
        val diffCallback = JokesDiffUtil(jokes, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        jokes.clear()
        jokes.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onBindViewHolder(holder: SelectedHolder, position: Int) {
        holder.bind(jokes[position], context)
        holder.itemView.setOnClickListener {
            val fragment = FragmentJokesDetails()
            fragmentManager.setFragmentResult(
                "jokeDetails",
                Bundle().apply {
                    putParcelableArrayList("jokesList", ArrayList(jokes))
                    putInt("jokeIndex", position)
                }
            )
            fragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, fragment)
                .commit()
        }
    }
}