package com.example.project.controllers

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.project.models.Users

@Database(entities = [Users::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun databaseDAO(): com.example.project.data.Database
}