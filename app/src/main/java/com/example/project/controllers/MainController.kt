package com.example.project.controllers

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.project.models.Users

class MainController private constructor() {
    private var database: AppDatabase? = null
    private var userList = ArrayList<Users>()

    private object HOLDER {
        val INSTANCE = MainController()
    }

    companion object {
        val instance: MainController by lazy { HOLDER.INSTANCE }
    }

    fun loadDatabase(applicationContext: Context) {

        database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

        try {
            database!!.databaseDAO().insertAllUsers(userList)
        } catch (ex: Exception) {
            Log.d("logdemo", "Records already in Database: ${ex.message}")
        }
    }
}