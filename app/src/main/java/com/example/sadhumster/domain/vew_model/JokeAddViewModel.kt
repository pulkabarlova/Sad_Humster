package com.example.sadhumster.domain.vew_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.sadhumster.datasource.db.AppDatabase
import com.example.sadhumster.domain.model.Joke
import com.example.sadhumster.domain.repository.JokeRepository
import kotlinx.coroutines.launch

class JokeAddViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: JokeRepository by lazy {
        val database = AppDatabase.getDatabase(application)
        JokeRepository(database.jokesDao(), database.cachedJokeDao())
    }
    fun addJoke(newJoke: Joke) {
        viewModelScope.launch {
            repository.addJoke(newJoke)
        }
    }
}