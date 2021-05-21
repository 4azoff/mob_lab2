package com.example.lab_1_v1

import android.app.Application
import androidx.room.Room



class App : Application() {
    var database: AppDatabase? = null
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, AppDatabase::class.java, "database")
            .allowMainThreadQueries()
            .build()
    }

    companion object {
        var instance: App? = null
    }
}