package com.example.mobileproject.presentation

import android.app.Application
import androidx.room.Room
import com.example.mobileproject.data.SportifyDatabase

class MyApp : Application() {
    lateinit var database: SportifyDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            SportifyDatabase::class.java,
            "app_database"
        ).build()
    }
}
