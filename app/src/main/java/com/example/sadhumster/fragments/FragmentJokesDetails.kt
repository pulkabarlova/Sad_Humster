package com.example.sadhumster.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sadhumster.joke.JokesData
import com.example.sadhumster.R
import com.example.sadhumster.databinding.FragmentJokesDetailsBinding


class FragmentJokesDetails : Fragment(R.layout.fragment_jokes_details) {

    private var _binding: FragmentJokesDetailsBinding? = null
    private val binding get() = _binding!!
    private val jokesData by lazy { JokesData }
    private var jokesList = JokesData.getJokesList()
    private val jokeIndex = "jokeIndex"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentJokesDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val jokesIndex = arguments?.getInt(jokeIndex)
        with(binding) {
            hamster.setImageResource(R.drawable.hamster)
            header.text = jokesList[jokesIndex!!].category
            question.text = jokesList[jokesIndex].question
            answer.text = jokesList[jokesIndex].answer
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}