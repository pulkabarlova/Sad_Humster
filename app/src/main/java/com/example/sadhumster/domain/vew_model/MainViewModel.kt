package com.example.sadhumster.domain.vew_model


import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.sadhumster.datasource.db.AppDatabase
import com.example.sadhumster.datasource.network.RetrofitInstance
import com.example.sadhumster.domain.model.Joke
import com.example.sadhumster.domain.model.JokeFromInternet
import com.example.sadhumster.domain.repository.JokeRepository
import com.example.sadhumster.presentation.fragments.FROM_INTERNET

import kotlinx.coroutines.launch


class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val jokesListLoaded = mutableSetOf<JokeFromInternet>()
    private val jokesLisFromFragment = mutableSetOf<Joke>()
    private val _jokesList = MutableLiveData<List<Joke>>()
    val jokesList: LiveData<List<Joke>> get() = _jokesList
    private val repository: JokeRepository by lazy {
        val database = AppDatabase.getDatabase(application)
        JokeRepository(database.jokesDao(), database.cachedJokeDao())
    }

    init {
        loadInitialData()
    }

    fun loadInitialData() {
        viewModelScope.launch {
            repository.getAllJokes().collect { jokesFromDb ->
                jokesLisFromFragment.addAll(jokesFromDb)
                updateCombinedData()
            }
        }
        viewModelScope.launch {
            repository.getAllJokesCached().collect { cachedJokes ->
                jokesListLoaded.addAll(cachedJokes)
                updateCombinedData()
            }
        }
    }

    private fun updateCombinedData() {
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
        _jokesList.value = combinedList
    }

    fun loadJokesFromApi() {
        viewModelScope.launch {
            try {
                val retrofitInstance = RetrofitInstance()
                repository.clearOldCache(System.currentTimeMillis() - 300_000)
                val response = retrofitInstance.api.loadJokes()
                if (response.isSuccessful) {
                    val loaded = response.body()
                    if (loaded != null) {
                        for (i in loaded.jokes) {
                            i.from = FROM_INTERNET
                            jokesListLoaded.add(i)
                            repository.addJokeCached(i)
                        }
                        updateCombinedData()
                    }
                }
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error loading jokes", e)
            }
        }
    }
}

