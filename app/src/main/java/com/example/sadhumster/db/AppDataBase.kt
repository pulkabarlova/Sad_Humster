package com.example.sadhumster.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sadhumster.model.Joke
import com.example.sadhumster.model.JokeFromInternet


@Database(entities = [Joke::class, JokeFromInternet::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun jokesDao(): JokesDao
    abstract fun cachedJokeDao(): CachedJokeDao

    companion object {
        @Volatile
        var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}