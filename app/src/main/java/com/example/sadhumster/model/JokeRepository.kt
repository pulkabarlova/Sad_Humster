package com.example.sadhumster.model

import android.view.View
import com.example.sadhumster.db.CachedJokeDao
import com.example.sadhumster.db.JokesDao
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class JokeRepository(private val jokesDao: JokesDao, private val cachedJokeDao: CachedJokeDao) {
    suspend fun addJoke(joke: Joke) {
        jokesDao.insertJoke(joke)
    }

    suspend fun addJokeCached(cachedJoke: JokeFromInternet) {
        cachedJokeDao.insertJoke(cachedJoke)
    }
    suspend fun getAllJokes(): Flow<List<Joke>> {
        return jokesDao.getAllJokes()
    }

    fun getAllJokesCached(): Flow<List<JokeFromInternet>> {
        return cachedJokeDao.getAllJokes()
    }

    suspend fun clearOldCache(validTime: Long) {
        return cachedJokeDao.clearOldCache(validTime)
    }
}