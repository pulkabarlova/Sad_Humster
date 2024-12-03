package com.example.sadhumster.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sadhumster.recycler_set_up.JokesAdapter
import com.example.sadhumster.R
import com.example.sadhumster.activities.MainActivity
import com.example.sadhumster.databinding.MainFragmentBinding
import com.example.sadhumster.db.AppDatabase
import com.example.sadhumster.db.CachedJokeDao
import com.example.sadhumster.db.JokesDao
import com.example.sadhumster.model.Joke
import com.example.sadhumster.model.JokeFromInternet
import com.example.sadhumster.model.JokeRepository
import com.example.sadhumster.network.RetrofitInstance
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

const val fromInternet = "fromInternet"
const val fromFragment = "fromFragment"

class MainFragment : Fragment(R.layout.main_fragment) {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy { JokesAdapter(this, parentFragmentManager) }
    private val jokesListLoaded = mutableSetOf<JokeFromInternet>()
    private val jokesLisFromFragment = mutableSetOf<Joke>()
    private var isFirst = true
    private var isLoading = false
    private val repository: JokeRepository by lazy {
        val database = AppDatabase.getDatabase(requireContext())
        JokeRepository(database.jokesDao(), database.cachedJokeDao())
    }

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
            jokesLisFromFragment.clear()
            jokesListLoaded.clear()
            //jokesLisFromFragment.addAll(savedInstanceState.getParcelableArrayList("jokesListFromFragment")!!)
            //jokesListLoaded.addAll(savedInstanceState.getParcelableArrayList("jokesListLoaded")!!)
            isFirst = savedInstanceState.getBoolean("isFirst")
        }
        setUpRecycler()
        viewLifecycleOwner.lifecycleScope.launch {
            repository.getAllJokes().collect {
                jokesLisFromFragment.addAll(it)
                updateAdapterData()
                binding.progressBar.visibility = View.INVISIBLE
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repository.getAllJokesCached().collect {
                jokesListLoaded.addAll(it)
                updateAdapterData()
                binding.progressBar.visibility = View.INVISIBLE
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            if (isFirst) {
                loadJokes()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //outState.putParcelableArrayList("jokesListFromFragment", ArrayList(jokesLisFromFragment))
        //outState.putParcelableArrayList("jokesListLoaded", ArrayList(jokesListLoaded))
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

    private suspend fun loadJokes() {
        binding.progressBar.visibility = View.VISIBLE
        val retrofitInstance = RetrofitInstance()
        repository.clearOldCache(System.currentTimeMillis() - 300_000)
        try {
            val response = retrofitInstance.api.loadJokes()
            if (response.isSuccessful) {
                val loaded = response.body()
                if (loaded != null) {
                    for (i in loaded.jokes) {
                        i.from = fromInternet
                        jokesListLoaded.add(i)
                        repository.addJokeCached(i)
                    }
                    updateAdapterData()
                } else {
                    Snackbar.make(binding.root, getString(R.string.error), Snackbar.LENGTH_SHORT)
                        .show()
                }
            } else {
                Snackbar.make(binding.root, getString(R.string.error), Snackbar.LENGTH_SHORT).show()
            }
        } catch (exception: Exception) {
            Snackbar.make(binding.root, getString(R.string.error), Snackbar.LENGTH_SHORT).show()
        } finally {
            binding.progressBar.visibility = View.INVISIBLE
        }
    }

    private fun updateAdapterData() {
        val combinedList = mutableListOf<Joke>()
        combinedList.addAll(jokesLisFromFragment)
        combinedList.addAll(jokesListLoaded.map { jokeFromInternet ->
            Joke(
                id = jokeFromInternet.id,
                category = jokeFromInternet.category,
                setup = jokeFromInternet.setup,
                delivery = jokeFromInternet.delivery,
                from = jokeFromInternet.from
            )
        })
        adapter.setData(combinedList)
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
                            loadJokes()
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
