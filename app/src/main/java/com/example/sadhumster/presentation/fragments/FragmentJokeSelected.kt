package com.example.sadhumster.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sadhumster.R
import com.example.sadhumster.databinding.FragmentJokeSelectedBinding
import com.example.sadhumster.domain.model.Joke
import com.example.sadhumster.domain.model.JokeFromInternet
import com.example.sadhumster.domain.vew_model.JokeSelectedViewModel
import com.example.sadhumster.domain.vew_model.MainViewModel
import com.example.sadhumster.presentation.recycler.JokesAdapter
import com.example.sadhumster.presentation.reycler_selected.SelectedAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentJokeSelected : Fragment(R.layout.fragment_joke_selected) {

    private var _binding: FragmentJokeSelectedBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: SelectedAdapter
    private val viewModel: JokeSelectedViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentJokeSelectedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = SelectedAdapter(this, parentFragmentManager, viewModel.repository)
        setUpRecycler()
        setupObservers()
        viewModel.loadInitialData()
    }

    private fun setUpRecycler() {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.jokesList.observe(viewLifecycleOwner) { jokes ->
            adapter.setData(jokes.toMutableList())
        }
        binding.progressBar.visibility = View.INVISIBLE
    }
}