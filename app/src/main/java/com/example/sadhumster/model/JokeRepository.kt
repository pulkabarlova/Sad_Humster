package com.example.sadhumster.model

import android.view.View
import com.example.sadhumster.db.CachedJokeDao
import com.example.sadhumster.db.JokesDao
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class JokeRepository(private val jokesDao: JokesDao, private val cachedJokeDao: CachedJokeDao) {
    val jokesList = mutableListOf<Joke>()

    /*
    suspend fun getData(): Flow<List<Joke>> = flow {
        emit(jokesList)
    }

    fun addJoke(joke: Joke) {
        jokesList.add(joke)
    }*/
    suspend fun addJoke(joke: Joke) {
        jokesDao.insertJoke(joke)
    }

    suspend fun addJokeCached(cachedJoke: JokeFromInternet) {
        cachedJokeDao.insertJoke(cachedJoke)
    }
    fun getAllJokes(): Flow<List<Joke>> {
        return jokesDao.getAllJokes()
    }

    fun getAllJokesCached(): Flow<List<JokeFromInternet>> {
        return cachedJokeDao.getAllJokes()
    }

}