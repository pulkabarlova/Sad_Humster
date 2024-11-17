package com.example.sadhumster.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sadhumster.model.JokesData
import com.example.sadhumster.R
import com.example.sadhumster.databinding.FragmentJokesDetailsBinding
import com.example.sadhumster.model.Joke


class FragmentJokesDetails : Fragment(R.layout.fragment_jokes_details) {

    private var _binding: FragmentJokesDetailsBinding? = null
    private val binding get() = _binding!!
    private val key1 = "jokeIndex"
    private val key2 = "jokesList"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentJokesDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentFragmentManager.setFragmentResultListener(
            "jokeDetails",
            viewLifecycleOwner
        ) { _, bundle ->
            val jokesList = bundle.getParcelableArrayList<Joke>(key2)
            val jokeIndex = bundle.getInt(key1)
            jokesList?.get(jokeIndex)?.let { joke ->
                with(binding) {
                    hamster.setImageResource(R.drawable.hamster)
                    header.text = "#" + "" + (jokeIndex + 1).toString() + " " + joke.category
                    question.text = joke.question
                    answer.text = joke.answer
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}