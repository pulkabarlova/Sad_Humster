package com.example.sadhumster.datasource.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    fun provideJokesDao(database: AppDatabase): JokesDao {
        return database.jokesDao()
    }

    @Provides
    fun provideCachedJokeDao(database: AppDatabase): CachedJokeDao {
        return database.cachedJokeDao()
    }
}