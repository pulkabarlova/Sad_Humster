package com.example.sadhumster.domain.vew_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.sadhumster.datasource.db.AppDatabase
import com.example.sadhumster.domain.model.Joke
import com.example.sadhumster.domain.repository.JokeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokeAddViewModel @Inject constructor(private val repository: JokeRepository) : ViewModel() {

    fun addJoke(newJoke: Joke) {
        viewModelScope.launch {
            repository.addJoke(newJoke)
        }
    }
}