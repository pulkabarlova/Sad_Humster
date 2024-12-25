package com.example.sadhumster.domain.vew_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sadhumster.domain.model.Joke
import com.example.sadhumster.domain.model.JokeFromInternet
import com.example.sadhumster.domain.repository.JokeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class JokeSelectedViewModel @Inject constructor(val repository: JokeRepository) :
    ViewModel() {

    private val jokesListLoaded = mutableSetOf<JokeFromInternet>()
    private val jokesLisFromFragment = mutableSetOf<Joke>()
    private val _jokesList = MutableLiveData<List<Joke>>()
    val jokesList: LiveData<List<Joke>> get() = _jokesList

    init {
        loadInitialData()
    }

    fun loadInitialData() {
        viewModelScope.launch {
            repository.getAllJokes().collect { jokesFromDb ->
                for (i in jokesFromDb) {
                    if (i.favourite == 1) {
                        jokesLisFromFragment.add(i)
                        updateCombinedData()
                    }
                }
            }
        }
        viewModelScope.launch {
            repository.getAllJokesCached().collect { cachedJokes ->
                for (i in cachedJokes) {
                    if (i.favourite == 1) {
                        jokesListLoaded.add(i)
                        updateCombinedData()
                    }
                }
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
                from = jokeFromInternet.from,
                favourite = jokeFromInternet.favourite
            )
        })
        _jokesList.value = combinedList
    }
}