package com.example.sadhumster.db

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sadhumster.model.Joke
import com.example.sadhumster.model.JokeFromInternet
import kotlinx.coroutines.flow.Flow

@Dao
interface JokesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllJokes(jokes: List<Joke>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJoke(joke: Joke)


    @Query("SELECT * FROM jokes")
    fun getAllJokes(): Flow<List<Joke>>
}

@Dao
interface CachedJokeDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllJokes(jokes: List<JokeFromInternet>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJoke(cachedJoke: JokeFromInternet)

    @Query("DELETE FROM jokes_from_internet WHERE jokes_from_internet < :validTime")
    suspend fun clearOldCache(validTime: Long)

    @Query("SELECT * FROM jokes_from_internet")
    fun getAllJokes(): Flow<List<JokeFromInternet>>
}