package com.example.sadhumster.datasource

import android.app.Application
import com.example.sadhumster.datasource.db.AppDatabase
import dagger.hilt.android.HiltAndroidApp


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppDatabase.INSTANCE = AppDatabase.getDatabase(this)
    }
}
