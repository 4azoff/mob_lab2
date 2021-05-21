package com.example.lab_1_v1

import androidx.room.Database
import androidx.room.RoomDatabase



@Database(entities = [MapMarker::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun markerDao(): MarkerDao
}