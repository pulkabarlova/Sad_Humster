package com.example.sadhumster.domain.repository

import com.example.sadhumster.datasource.db.CachedJokeDao
import com.example.sadhumster.datasource.db.JokesDao
import com.example.sadhumster.domain.model.Joke
import com.example.sadhumster.domain.model.JokeFromInternet
import kotlinx.coroutines.flow.Flow


class JokeRepository (private val jokesDao: JokesDao, private val cachedJokeDao: CachedJokeDao) {
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