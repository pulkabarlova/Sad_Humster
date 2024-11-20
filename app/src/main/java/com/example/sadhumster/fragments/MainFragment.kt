package com.example.sadhumster.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sadhumster.recycler_set_up.JokesAdapter
import com.example.sadhumster.model.JokesData
import com.example.sadhumster.R
import com.example.sadhumster.activities.MainActivity
import com.example.sadhumster.databinding.MainFragmentBinding
import com.example.sadhumster.model.Joke
import com.example.sadhumster.model.JokeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainFragment : Fragment(R.layout.main_fragment) {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy { JokesAdapter(this, parentFragmentManager) }
    private val jokeRepository = JokeRepository
    private val jokesList = mutableListOf<Joke>()
    private var isFirst = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBar.visibility = View.INVISIBLE
        binding.floatingActionButtonFragment1.setOnClickListener {
            onAddClick()
        }
        if (savedInstanceState != null) {
            jokesList.clear()
            jokesList.addAll(savedInstanceState.getParcelableArrayList("jokesList")!!)
            isFirst = savedInstanceState.getBoolean("isFirst")
        }
        setUpRecycler()
        viewLifecycleOwner.lifecycleScope.launch {
            if(!isFirst){
                binding.progressBar.visibility = View.VISIBLE
                binding.enter.visibility = View.INVISIBLE
            }
            jokeRepository.getData().collect{
                jokesList.addAll(it)
                adapter.setData(jokesList)
                binding.progressBar.visibility = View.INVISIBLE
            }
        }
    }

    private fun setUpRecycler() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        adapter.setData(jokesList)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("jokesList", ArrayList(jokesList))
        outState.putBoolean("isFirst", isFirst)
    }

    private fun onAddClick() {
        isFirst = false
        val currentFragment = parentFragmentManager.findFragmentByTag("main")
        if (currentFragment != null) {
            parentFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, FragmentJokeAdd())
                .commit()
        }
    }
}
