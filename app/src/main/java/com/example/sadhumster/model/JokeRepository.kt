package com.example.sadhumster.model

import android.view.View
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object JokeRepository {
    val jokesList = mutableListOf<Joke>()
    suspend fun getData(): Flow<List<Joke>> = flow{
        emit(jokesList)
    }
    suspend fun addJoke(joke: Joke){
        jokesList.add(joke)
    }
}