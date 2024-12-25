package com.example.sadhumster.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sadhumster.presentation.recycler.JokesAdapter
import com.example.sadhumster.R
import com.example.sadhumster.databinding.MainFragmentBinding
import com.example.sadhumster.domain.model.Joke
import com.example.sadhumster.domain.model.JokeFromInternet
import com.example.sadhumster.domain.vew_model.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

const val FROM_INTERNET = "fromInternet"
const val FROM_FRAGMENT = "fromFragment"
const val JOKE_INDEX_KEY = "jokeIndex"
const val JOKE_LIST_KEY = "jokesList"

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: JokesAdapter
    private val jokesListLoaded = mutableSetOf<JokeFromInternet>()
    private val jokesLisFromFragment = mutableSetOf<Joke>()
    private var isFirst = true
    private var isLoading = false
    private val viewModel: MainViewModel by viewModels()


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
        adapter = JokesAdapter(this, parentFragmentManager)
        binding.progressBar.visibility = View.INVISIBLE
        binding.floatingActionButtonFragment1.setOnClickListener {
            onAddClick()
        }
        if (savedInstanceState != null) {
            jokesLisFromFragment.clear()
            jokesListLoaded.clear()
            isFirst = savedInstanceState.getBoolean("isFirst")
        }
        setUpRecycler()
        setupObservers()
        viewModel.loadInitialData()
        if (isFirst) {
            viewModel.loadJokesFromApi()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("isFirst", isFirst)
    }

    private fun setupObservers() {
        viewModel.jokesList.observe(viewLifecycleOwner) { jokes ->
            adapter.setData(jokes.toMutableList())
            binding.progressBar.visibility = View.INVISIBLE
        }
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

    private fun setUpRecycler() {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 && !isLoading) {
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount) {
                        isLoading = true
                        binding.progressBar.visibility = View.VISIBLE
                        viewLifecycleOwner.lifecycleScope.launch {
                            viewModel.loadJokesFromApi()
                            isLoading = false
                        }
                    }
                }
            }
        })
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
