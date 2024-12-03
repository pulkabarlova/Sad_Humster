package com.example.sadhumster.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.launch
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.lifecycleScope
import com.example.sadhumster.R
import com.example.sadhumster.activities.MainActivity
import com.example.sadhumster.databinding.FragmentJokeAddBinding
import com.example.sadhumster.databinding.FragmentJokesDetailsBinding
import com.example.sadhumster.db.AppDatabase
import com.example.sadhumster.model.Joke
import com.example.sadhumster.model.JokeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FragmentJokeAdd : Fragment(R.layout.fragment_joke_add) {

    private var _binding: FragmentJokeAddBinding? = null
    private val binding get() = _binding!!
    private val repository: JokeRepository by lazy {
        JokeRepository(
            AppDatabase.INSTANCE?.jokesDao() ?: error("Database not initialized"),
            AppDatabase.INSTANCE?.cachedJokeDao() ?: error("Database not initialized")
        )
    }

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
            from = fromFragment
        )
        viewLifecycleOwner.lifecycleScope.launch {
            repository.addJoke(newJoke)
            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}