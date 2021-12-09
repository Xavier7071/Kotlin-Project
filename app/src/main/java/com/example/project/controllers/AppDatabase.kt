package com.example.project.controllers

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.project.models.*

@Database(entities = [Users::class, Players::class, Coaches::class, Game::class, Game_User::class, Team_User::class, Teams::class], version = 9)
@TypeConverters(DateConverter::class, BitMapConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun databaseDAO(): com.example.project.data.Database
}