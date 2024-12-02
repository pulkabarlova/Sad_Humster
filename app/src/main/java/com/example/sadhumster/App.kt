package com.example.sadhumster

import android.app.Application
import com.example.sadhumster.db.AppDatabase

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppDatabase.INSTANCE = AppDatabase.getDatabase(this)
    }
}
