package com.example.sadhumster.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sadhumster.R
import com.example.sadhumster.databinding.FragmentJokesDetailsBinding
import com.example.sadhumster.domain.model.Joke


class FragmentJokesDetails : Fragment(R.layout.fragment_jokes_details) {

    private var _binding: FragmentJokesDetailsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJokesDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentFragmentManager.setFragmentResultListener(
            "jokeDetails",
            viewLifecycleOwner
        ) { _, bundle ->
            val jokesList = bundle.getParcelableArrayList<Joke>(JOKE_LIST_KEY)
            val jokeIndex = bundle.getInt(JOKE_INDEX_KEY)
            jokesList?.get(jokeIndex)?.let { joke ->
                var from: String = ""
                when (joke.from) {
                    "fromInternet" -> from = context?.getString(R.string.from_internet).toString()
                    "fromFragment" -> from = context?.getString(R.string.from_fragment).toString()
                }
                with(binding) {
                    hamster.setImageResource(R.drawable.hamster)
                    val s = context?.getString(
                        R.string.joke_title,
                        (jokeIndex + 1),
                        joke.category,
                        from
                    )
                    header.text = s
                    question.text = joke.setup
                    answer.text = joke.delivery
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
