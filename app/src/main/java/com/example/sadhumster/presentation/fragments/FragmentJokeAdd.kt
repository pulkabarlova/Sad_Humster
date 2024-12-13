package com.example.sadhumster.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.sadhumster.R
import com.example.sadhumster.databinding.FragmentJokeAddBinding
import com.example.sadhumster.datasource.db.AppDatabase
import com.example.sadhumster.domain.model.Joke
import com.example.sadhumster.domain.repository.JokeRepository
import com.example.sadhumster.domain.vew_model.JokeAddViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class FragmentJokeAdd : Fragment(R.layout.fragment_joke_add) {

    private var _binding: FragmentJokeAddBinding? = null
    private val binding get() = _binding!!
    private val viewModel: JokeAddViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJokeAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.floatingActionButtonFragment.setOnClickListener {
            addJoke()
        }
    }

    private fun addJoke() {
        val question = binding.question.text.toString()
        val answer = binding.answer.text.toString()
        val category = binding.name.text.toString()
        val newJoke = Joke(
            category = category,
            setup = question,
            delivery = answer,
            from = FROM_FRAGMENT
        )
        viewModel.addJoke(newJoke)
        parentFragmentManager.popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}